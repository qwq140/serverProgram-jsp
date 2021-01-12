package com.cos.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import com.cos.board.domain.user.dto.JoinReqDto;
import com.cos.board.service.UserService;
import com.cos.board.util.Script;

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
			
		} else if(cmd.equals("login")) {
			
		}
	}
}
