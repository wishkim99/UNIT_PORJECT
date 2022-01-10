//package controller;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
// 
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
// 
////import com.mybulletin.web.entity.Notice;
////import com.mybulletin.web.service.NoticeService;
//
//import model.dao.WritingDAO;
//import model.vo.WritingVO;
//@WebServlet("/list")
//public class ListController extends HttpServlet{
//    @Override        
//    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    			throws ServletException, IOException {
//    		String field_ = request.getParameter("f");
//    		String query_ = request.getParameter("q");
//    		String page_ = request.getParameter("p");
//
//    		String field = "title";
//    		if (field_ != null && !field_.equals(""))
//    			field = field_;
//
//    		String query = "";
//    		if (query_ != null && !query_.equals(""))
//    			query = query_;
//
//    		int page = 1;
//    		if (page_ != null && !page_.equals(""))
//    			page = Integer.parseInt(page_);
//
//    		WritingDAO service = new WritingDAO();
//    		ArrayList<WritingVO> listAll = service.listAll(field, query, page);
//
//    		request.setAttribute("list", listAll);
//    		request.getRequestDispatcher("/jsp/writingMain.jsp").forward(request, response);
//    }
//}