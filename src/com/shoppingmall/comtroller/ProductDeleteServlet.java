package com.shoppingmall.comtroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingmall.dao.ProductDAO;
import com.shoppingmall.dto.ProductVO;

@WebServlet("/productDelete.do")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //선택한 항목의 코드가져와  매개로 조회 후 productDelete.jsp로 이동 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductVO pVo = new ProductVO();
		
		//code받고 조회
		String code = request.getParameter("code");
		System.out.println(code);
		ProductDAO pDao = ProductDAO.getInstance();
		pVo = pDao.selectProductBycode(code);
		
		//영역객체로 전달
		request.setAttribute("product", pVo);
		
		
		//페이지이동  forward방식은 브라우저 url이 바뀌지 않는다
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productDelete.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//여기서 delete메소드 실행
		ProductDAO pDao = ProductDAO.getInstance();
		
		String code  = request.getParameter("code");
		
		//code 받아와야지 어디서?  deleteProduct.jsp 에 hidden 폼에서
		pDao.deleteProduct(code);
		
		/*
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("productList.do"); dispatcher.forward(request,
		 * response);
		 */
//		이 둘 차이를 아직 모르나봄
		response.sendRedirect("productList.do");
	}

}
