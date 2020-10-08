package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.IFavouriteService;
import cn.itcast.travel.service.IRouteService;
import cn.itcast.travel.service.Impl.favouriteServiceImpl;
import cn.itcast.travel.service.Impl.routeSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/route/*")
public class routeServlet extends baseServlet {
    private IRouteService iRouteService = new routeSerivceImpl();
    private IFavouriteService iFavouriteService = new favouriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        Integer cid = 0;
        Integer pageSize;
        Integer currentPage;
        if (cidStr !=null && cidStr.length()> 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        if (pageSizeStr !=null && pageSizeStr.length()> 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }
        if (currentPageStr !=null && currentPageStr.length()> 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        pageBean<Route> routepageBean = iRouteService.pageQuery(cid, currentPage, pageSize, rname);
        writeValue(routepageBean,response);
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        Route route = iRouteService.findOne(rid);
        writeValue(route,response);
    }

    public void isFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer uid;
        if (user == null){
            uid = 0;
        }else{
            uid = user.getUid();
        }
        Boolean flag = iFavouriteService.isFavourite(rid, uid);
        writeValue(flag,response);
    }
    public void insertNewFav(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Integer uid;
        if (user == null){
            return;
        }else{
            uid = user.getUid();
        }
        String rid = request.getParameter("rid");
        iFavouriteService.insertNewFav(uid,rid);
    }


}
