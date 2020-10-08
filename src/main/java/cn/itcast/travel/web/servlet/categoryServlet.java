package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.ICategoryService;
import cn.itcast.travel.service.Impl.categoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class categoryServlet extends baseServlet {
    private ICategoryService service = new categoryServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper();
    public void findAll (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> all = service.findAll();
        String s = objectMapper.writeValueAsString(all);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }

}
