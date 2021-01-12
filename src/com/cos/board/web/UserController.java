package com.cos.board.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import com.cos.board.domain.user.User;
import com.cos.board.domain.user.dto.JoinReqDto;
import com.cos.board.domain.user.dto.LoginReqDto;
import com.cos.board.service.UserService;
import com.cos.board.util.Script;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		if(cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<User> users = userService.유저목록(page);
			request.setAttribute("users", users);
			
			int userCount = userService.유저수();
			int lastPage = (userCount-1)/8; 
			request.setAttribute("lastPage", lastPage);
			List<Integer> pageCount = new ArrayList<>();
			for (int i = 0; i < lastPage+1; i++) {
				pageCount.add(i+1);
			}
			request.setAttribute("pageCount", pageCount);
			
			RequestDispatcher dis = request.getRequestDispatcher("user/userList.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("joinForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/join.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			
			int result = userService.회원가입(dto);
			
			if(result == 1) {
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "회원가입에 실패하셨습니다.");
			}
		} else if(cmd.equals("loginForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			
			User userEntity = userService.로그인(dto);
//			System.out.println("userEntity : "+userEntity);
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "로그인에 실패하셨습니다.");
			}
			
		} else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		} else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int result = userService.삭제(id);
			
			if(result == 1) {
				HttpSession session = request.getSession();
				User principal = (User)session.getAttribute("principal");
//				System.out.println("principal : "+principal);
				if(principal.getUserRole().equals("admin")) {
					response.sendRedirect("index.jsp");
				} else {
					session.invalidate();
					response.sendRedirect("index.jsp");
				}
			} else {
				Script.back(response, "삭제 실패");
			}

		}
	}
}
