package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.shop.dto.ProductVo;
import com.shop.util.DBManager;

public class ProductDao {

	// *** 싱글톤
	// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
	// 2. 객체 생성 private static : 자신이 객체를 생성
	// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공
	private ProductDao() {		
	}
	
	private static ProductDao instance = new ProductDao();
	
	public static ProductDao getInstance() {
		return instance;
	}
	
	// 상품 등록
	// 입력값 : 전체 상품 정보
	// 반환값 : 쿼리 수행 결과
	public int insertProduct(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
//		String sql_insert = "insert into product values(?, ?, ?, ?, ?, ?)";
		String sql_insert = "insert into product values(product_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_insert);
			
//			pstmt.setInt(1, pVo.getCode());
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());			// 정수형
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());	// 문자형
			pstmt.setDate(5, pVo.getReg_date());		// 날짜형
			pstmt.setString(6, pVo.getArrivalAdd());
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
		
	}

	// 전체 상품 조회
	public List<ProductVo> selectAllProducts() {
		String sql = "select * from product order by code desc";
		
//		ProductVo[] listArr = new ProductVo[100];	// 상품 100개 저장 가능한 배열 선언
//		int countList = 0;
		List<ProductVo> list = new ArrayList<ProductVo>();	// List 컬렉션 객체 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명");
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code"));		// 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				pVo.setArrivalAdd(rs.getString("arrivalAdd"));
//				System.out.println(rs.getInt("pictureurl"));
				
//				listArr[countList] = pVo;
//				countList++;
				list.add(pVo);		// list 객체에 데이터 추가
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 전체 주문 조회
	public List<ProductVo> selectAllOrders() {
		String sql_order = "select * from product where code like '%2%' or code like '%3%' or code like '%5%' or code like '%8%' "
				+ "order by code desc";
		
//		ProductVo[] listArr = new ProductVo[100];	// 상품 100개 저장 가능한 배열 선언
//		int countList = 0;
		List<ProductVo> orderList = new ArrayList<ProductVo>();	// List 컬렉션 객체 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_order);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명");
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code"));		// 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				pVo.setArrivalAdd(rs.getString("arrivalAdd"));
//				System.out.println(rs.getInt("pictureurl"));
				
//				listArr[countList] = pVo;
//				countList++;
				orderList.add(pVo);		// list 객체에 데이터 추가
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}	
	
	
	// 오늘 매출 리스트
	public List<ProductVo> selectTodaySales() {
		String sql_today_sales = "select * from product where code like '%3%' or code like '%5%' or code like '%8%' "
				+ "order by code desc";
		
//		ProductVo[] listArr = new ProductVo[100];	// 상품 100개 저장 가능한 배열 선언
//		int countList = 0;
		List<ProductVo> salesTodayList = new ArrayList<ProductVo>();	// List 컬렉션 객체 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_today_sales);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명");
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code"));		// 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				pVo.setArrivalAdd(rs.getString("arrivalAdd"));
//				System.out.println(rs.getInt("pictureurl"));
				
//				listArr[countList] = pVo;
//				countList++;
				salesTodayList.add(pVo);		// list 객체에 데이터 추가
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return salesTodayList;
	}	
	
	// 한달 매출 리스트
	public List<ProductVo> selectMonthSales() {
		String sql_month_sales = "select * from product order by code desc";
		
//		ProductVo[] listArr = new ProductVo[100];	// 상품 100개 저장 가능한 배열 선언
//		int countList = 0;
		List<ProductVo> salesMonthList = new ArrayList<ProductVo>();	// List 컬렉션 객체 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_month_sales);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명");
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code"));		// 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				pVo.setArrivalAdd(rs.getString("arrivalAdd"));
//				System.out.println(rs.getInt("pictureurl"));
				
//				listArr[countList] = pVo;
//				countList++;
				salesMonthList.add(pVo);		// list 객체에 데이터 추가
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return salesMonthList;
	}	

	// 단일 상품 조회 (상품코드) => 단일 상품 정보 반환
	public ProductVo selectProductByCode(String code) {		
		String sql = "select * from product where code=?";		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVo pVo = null;
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명");
				pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code"));		// 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pVo;
	}
	
	// 상품 수정 (수정정보) => 디비에 정상 반영 여부 반환
	public int updateProduct(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql = "update product set name=?, price=?, pictureurl=?, description=?, reg_date=?, arrivalAdd=? where code=?";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setDate(5, pVo.getReg_date());
			pstmt.setString(6, pVo.getArrivalAdd());
			pstmt.setInt(7, pVo.getCode());
			
			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();		// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 오더 수정 (수정정보) => 디비에 정상 반영 여부 반환
	public int updateOder(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql = "update product set name=?, price=?, pictureurl=?, description=?, reg_date=?, arrivalAdd=? where code=?";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setDate(5, pVo.getReg_date());
			pstmt.setString(6, pVo.getArrivalAdd());
			pstmt.setInt(7, pVo.getCode());
			
			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();		// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	
	
	// 상품 삭제
	public void deleteProduct(String code) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql = "delete from product where code=?";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, code);
			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	
	
	
	
	public List<ProductVo> selectcart() {
		String sql = "select * from cart order by code desc";

		List<ProductVo> list = new ArrayList<ProductVo>(); // List 컬렉션 객체 생성

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {

				// rs.getInt("컬럼명");

				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code")); // 컬럼명 name인 정보를 가져옴
				pVo.setName(rs.getString("name")); // DB에서 가져온 정보를 mVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("Pictureurl"));
				pVo.setDescription(rs.getString("Description"));
				pVo.setCnt(0);
				
				list.add(pVo); // List 객체에 데이터 추가
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int insertCart(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
//			Statement stmt = null;				// 정적 쿼리
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null; // 동적 쿼리

//			String sql_insert = "insert into member values('"+name+"', '"+id+"', '"+pwd+"', '"+email+"', '"+phone+"', "+admin+")";
//		String sql_insert = "insert into product values(?, ?, ?, ?, ?, ?)";
		// 시퀀스 적용
		String sql_insert = "insert into cart values(cart_seq.nextval, ?, ?, ?, ?, ?)";

//			System.out.println(sql_insert);

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
//				stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql_insert);

//			pstmt.setInt(1, pVo.getCode());
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice()); // 정수형
			pstmt.setString(3, pVo.getPictureurl()); // 문자형
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCnt()); //

			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
//				result = stmt.executeUpdate(sql_insert);
			result = pstmt.executeUpdate(); // 쿼리 수행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	
	public ProductVo selectCartByCode(String name) {
		String sql = "select * from cart where name=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVo pVo = null;

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {
				// rs.getInt("컬럼명");
				pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code")); // 컬럼명 name인 정보를 가져옴
				pVo.setName(rs.getString("name")); // DB에서 가져온 정보를 mVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("Pictureurl"));
				pVo.setDescription(rs.getString("Description"));
				pVo.setReg_date(rs.getDate("Reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pVo;
	}
	
	public void deleteCart() {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql ="delete from cart";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);	
			
			//pstmt.setInt(1, pVo.getCode());
			
	
		
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBManager.close(conn, pstmt);
//		
		}
		
	}
	
	public List<ProductVo> selectAllProduct() {
		String sql = "select * from product order by code desc";

		List<ProductVo> list = new ArrayList<ProductVo>(); // List 컬렉션 객체 생성

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {

				// rs.getInt("컬럼명");

				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code")); // 컬럼명 name인 정보를 가져옴
				pVo.setName(rs.getString("name")); // DB에서 가져온 정보를 mVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("Pictureurl"));
				pVo.setDescription(rs.getString("Description"));
				pVo.setReg_date(rs.getDate("Reg_date"));
				
				
				list.add(pVo); // List 객체에 데이터 추가

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<ProductVo> searchProduct(String column, String keyword) {
		String sql = "select * from product where" + column + "like ? order by code desc";
		
		List<ProductVo> list = new ArrayList<ProductVo>();
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {
				// rs.getInt("컬럼명");
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원 정보 획득
				pVo.setCode(rs.getInt("code")); // 컬럼명 name인 정보를 가져옴
				pVo.setName(rs.getString("name")); // DB에서 가져온 정보를 mVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("Pictureurl"));
				pVo.setDescription(rs.getString("Description"));
				pVo.setReg_date(rs.getDate("Reg_date"));
				
				list.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	
	}
	

}
