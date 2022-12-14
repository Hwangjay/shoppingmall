package com.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.MemberDao;
import com.shop.dto.MemberVo;

@WebServlet("/deleteMember.do")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리스트링으로 전달 받은 code를 획득
		String userid = request.getParameter("userid");
		
		// 상품 삭제 링크 클릭시 삭제할 상품 정보를 표시
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
				
		// 데이터베이스에서 삭제할 데이터 정보 확인		
		mVo = mDao.getMember(userid);
		
		request.setAttribute("member", mVo);
		
		// 페이지 이동 : 삭제 페이지
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("manager/member/deleteMember.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 삭제 코드 : 데이터베이스에서 상품 삭제
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
		
		String userid = request.getParameter("userid");
		
		// 데이터베이스로 부터 해당 코드의 정보를 삭제
		mDao.deleteMember(userid);
		
		// 삭제 후 목록 페이지로 이동
		response.sendRedirect("memberList.do");
	}
}
