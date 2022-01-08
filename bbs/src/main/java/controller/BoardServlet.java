package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import model.dao.MemberDAO;
import model.dao.WritingDAO;
import model.vo.MemberVO;
import model.vo.WritingVO;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String strId = request.getParameter("id");

		String searchOption = request.getParameter("opt");
		String condition = request.getParameter("condition");

		int id = 0;

		if (strId != null)
			id = Integer.parseInt(request.getParameter("id"));

		String action = request.getParameter("action");

		String writer = request.getParameter("writer");
		String target_url = "/jsp/writingMain.jsp";

		HttpSession session = request.getSession();

		if (session.getAttribute("state") == null) {
			session.setAttribute("state", "nonMember");
		}

		WritingDAO dao = new WritingDAO();

		if (action != null) {

			if (action.equals("search")) {
				ArrayList<WritingVO> list = dao.search(searchOption, condition);
				if (list != null && list.size() == 0) {
					request.setAttribute("msg", keyword + "(이)가 포함된 글이 없습니다.");
					System.out.println("찾는 글이 하나도 없음");
				} else {
					request.setAttribute("list", list);
				}
			} else {
				if (action.equals("select")) { // show detail content
					System.out.println(id);
					boolean result = dao.viewCntIncrease(id);
					if (!result)
						request.setAttribute("msg", "viewCnt DB Error..");

					WritingVO vo = dao.listOne(id);
					System.out.println(vo.getWriter());
					request.setAttribute("select_list", vo);
					target_url = "/jsp/contentView.jsp";

				} else if (action.equals("logout")) {
					session.setAttribute("state", "nonmember");

				} else if (action != null && action.equals("update")) { // 수정
					// vo.setId(Integer.parseInt(action));
//					WritingVO vo = dao.listOne(Integer.parseInt(id));
					WritingVO vo = dao.listOne(id);
					System.out.println(vo.getWriter());
//					request.setAttribute("select_list", vo);
//					WritingVO vo1 = (WritingVO) request.getAttribute("select_list");
					if (session.getAttribute("id").equals(vo.getWriter())) {
						System.out.println("dddddddddd");
						request.setAttribute("updatevo", vo);
						target_url = "/jsp/updateView.jsp";
					} else {
						System.out.println("content");
						request.setAttribute("updatevo", vo);
						target_url = "/jsp/contentView.jsp";
					}

				} else { // delete
					boolean result = dao.delete(id);
					if (result) {
						request.setAttribute("msg", "글이 성공적으로 삭제되었습니다.");
					} else {
						request.setAttribute("msg", "글이 삭제되지 않았습니다.");
					}
				}
				request.setAttribute("list", dao.listAll());

			}

		} else {
			request.setAttribute("list", dao.listAll());
		}

		request.getRequestDispatcher(target_url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// Writing 정보
		String id = request.getParameter("id");

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writedate = request.getParameter("writedate");

		String action = request.getParameter("action");

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		// 회원, 비회원 구분
		HttpSession session = request.getSession();

		if (session.getAttribute("state") == null) {
			session.setAttribute("state", "nonMember");
		}

		WritingDAO dao = new WritingDAO();
		WritingVO vo = new WritingVO();

		MemberVO mvo = new MemberVO();
		MemberDAO mdao = new MemberDAO();

		mvo.setId(id);
		mvo.setName(name);
		mvo.setPassword(password);
		mvo.setPhone(phone);

		// vo.setId(0);
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);

		LocalDate currentdate = LocalDate.now();
		vo.setWriteDate(currentdate.toString());

		String target_url = "/jsp/writingMain.jsp";

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (action != null) {

			if (action.equals("`1")) {

				boolean result = dao.insert(vo);
				if (result) {
					request.setAttribute("msg", name + "님의 글이 성공적으로 입력되었습니다.");
				} else {
					request.setAttribute("msg", name + "님의 글이 입력되지 않았습니다.");
				}
			} else if (action.equals("login")) { // 로그인 화면으로부터 넘어온 정보 처리
				// 로그인 정보 처리, 중복 확인을 하고 괜찮으면 로그인 성공!, 아니면 로그인 실패!
				boolean result = mdao.login(id, password);

				if (result) {
					System.out.println("Login Success!!");
					session.setAttribute("state", "member");
					session.setAttribute("id", id);
					session.setAttribute("writer", writer);

					out.println("<script>alert('Login Success!'); location.href='/bbs/board';</script>");
					out.close();

				} else {
					out.println("<script>alert('Login fail!'); location.href='/bbs/jsp/login.jsp';</script>");
					out.close();
					System.out.println("Login fail!!!");
				}

			} else if (action.equals("signup")) {

				if (id == null || password == null || name == null || phone == null) {
					out.println("<script>alert('Signup fail!'); location.href='/bbs/jsp/signup.jsp';</script>");
					out.close();
					request.setAttribute("msg", "회원 가입 실패 정보를 올바르게 입력해주세요");
				} else {
					boolean result = mdao.signup(mvo);

					if (result) {

						out.println("<script>alert('Signup Success!'); location.href='/bbs/board';</script>");
						out.close();

						// request.setAttribute("msg", "회원가입 성공");
						// request.getRequestDispatcher("/jsp/writingMain.jsp").forward(request,
						// response);
					} else {
						out.println("<script>alert('Signup fail!'); location.href='/bbs/jsp/signup.jsp';</script>");
						out.close();
//						target_url = "/bbs/jsp/signup.jsp";
//						System.out.println("회원 가입 실패 ");

					}
				}
			} else if (action != null && action.equals("update")) { // 수정
				// vo.setId(Integer.parseInt(action));
				vo.setId(Integer.parseInt(id));
				boolean result = dao.update(vo);
				if (result) {
					System.out.println("Update success");
					request.setAttribute("msg", name + "님의 글이 성공적으로 수정되었습니다.");
//					request.getRequestDispatcher("/jsp/writingMain.jsp").forward(request, response);
				} else {
					System.out.println("Update fail");
					request.setAttribute("msg", name + "님의 글이 수정되지 않았습니다.");
//					request.getRequestDispatcher("/jsp/writingMain.jsp").forward(request, response);
				}
			}

		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jsp/writingMain.jsp").forward(request, response);

	}

}
