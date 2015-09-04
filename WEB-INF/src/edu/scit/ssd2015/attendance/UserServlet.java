package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public UserServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		
		System.out.println("Create testlina");
		//(user_id,password,user_type,programme_id,batch_id,email),
		User user = new User(7, "Updatyna", "test@scit.edu", "testlina", "password", 1, 1, 1);
		user.save();
//		(int id, String name, String email, String userID,
//				String password, int userType, int programmeID, int batchID)
		String forward = null;
		if (id != null) {
			forward = JSP_BASE + "User.jsp";
		} else {
			throw new ServletException("Failed");
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
