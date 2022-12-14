package com.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.dto.ProductVo;

@WebServlet("/salesList.do")
public class SalesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao pDao = ProductDao.getInstance();
				
		// 모든 상품 리스트를 디비로부터 조회하여 저장
//		ProductVo[] productList = pDao.selectAllProducts();
//		System.out.println(productList[0]);
		//오늘 매출
		List<ProductVo> salesTodayList = pDao.selectTodaySales();		
		request.setAttribute("salesTodayList", salesTodayList);
		//한달 매출
		List<ProductVo> salesMonthList = pDao.selectMonthSales();		
		request.setAttribute("salesMonthList", salesMonthList);
//		System.out.println(productList.size());
//		System.out.println(productList.get(0));
		
		// 리스트 페이지로 이동
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("manager/sales/salesList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}


