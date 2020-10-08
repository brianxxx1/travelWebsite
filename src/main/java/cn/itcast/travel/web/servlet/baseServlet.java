package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/baseServlet")
public class baseServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    public void writeValue(Object object,HttpServletResponse resp) throws IOException {
        String s = objectMapper.writeValueAsString(object);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(s);
    }
    public String writeValueAsJson(Object object,HttpServletResponse resp) throws IOException {
        String s = objectMapper.writeValueAsString(object);
        return s;
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String method = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Method method1 = null;
        try {
            method1 = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
