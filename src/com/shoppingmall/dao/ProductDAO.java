package com.shoppingmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoppingmall.dto.ProductVO;
import com.shoppingmall.util.DBmanager;

public class ProductDAO {

	private ProductDAO() {

	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	// CRUD 중 Read Start
	public List<ProductVO> selectAllProducts() {
		String sql = "SELECT * FROM PRODUCT ORDER BY CODE DESC";

		List<ProductVO> list = new ArrayList<ProductVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBmanager.getConnection(); // DB연결..

			pstmt = conn.prepareStatement(sql); // sql준비해주고

			rs = pstmt.executeQuery(); // 쿼리문 실행, 결과

			while (rs.next()) { // 탐색 이동은 행 단위
				ProductVO pVo = new ProductVO();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureUrl(rs.getString("PICTURE"));
				pVo.setDescription(rs.getString("description"));

				System.out.println("상품명 : " + rs.getString("picture"));
				list.add(pVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBmanager.close(conn, pstmt, rs); // 메소드 선언부는 stmt 인데 괜찮은가???????
		}

		return list;

	}// CRUD 중 Read End

	// CRUE Read Start (code에 해당하는 상품정보 조회)
	public ProductVO selectProductBycode(String code) {

		ProductVO pVo = null;
		String sql = "SELECT * FROM PRODUCT WHERE CODE = ?";

		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBmanager.getConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, code);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					pVo = new ProductVO();

					pVo.setName(rs.getString("NAME"));
					pVo.setCode(rs.getInt("CODE"));
					pVo.setDescription(rs.getString("DESCRIPTION"));
					pVo.setPictureUrl(rs.getString("PICTURE"));
					pVo.setPrice(rs.getInt("PRICE"));

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBmanager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pVo;
	}

	// CRUD 중 Create Start
	public void insertProduct(ProductVO pVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO PRODUCT VALUES(PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";

		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}
	}// CRUD 중 Create End

	// CRUD 중 Update Start
	public void updateProduct(ProductVO pVo) { // (수정폼에입력한)pVo 매개변수로 받아 쿼리 실행
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE PRODUCT SET NAME=?, PRICE=?, PICTURE=?, DESCRIPTION=? WHERE CODE=?";

		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCode());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}

	}// CRUD 중 Update End
	
	
	//CRUD 중 Delete Start
	public void deleteProduct(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE PRODUCT WHERE CODE = ?";
		
		try {
			
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code); 					//매개변수 code (productList에서 가져옴)
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}
		
	}//Delete End
}
