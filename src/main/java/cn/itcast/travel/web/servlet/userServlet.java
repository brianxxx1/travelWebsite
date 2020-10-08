package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import cn.itcast.travel.service.Impl.userServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class userServlet extends baseServlet {
    private IUserService userService = new userServiceImpl();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpSession session = request.getSession();
            String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
            BeanUtils.populate(user,parameterMap);
            if (checkcode_server.equalsIgnoreCase(request.getParameter("check"))){

                boolean b = userService.userRegister(user);

                if (b){
                    resultInfo.setFlag(true);

                }
                else {
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("register failed");
                }

                String s = objectMapper.writeValueAsString(resultInfo);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(s);
            }
            else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("wrong checkcode");
                String s = objectMapper.writeValueAsString(resultInfo);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(s);
            }}
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void getUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        User user1 = userService.userLogin(user);
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        if (user1 != null){
            if ("Y".equals(user1.getStatus())){
                resultInfo.setFlag(true);
                HttpSession session = request.getSession();
                session.setAttribute("user",user1);
            }else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户尚未激活");
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码错误");
        }
        String s = objectMapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
