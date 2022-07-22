package com.shoppingmall.comtroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.DefaultBoundedRangeModel;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shoppingmall.dao.ProductDAO;
import com.shoppingmall.dto.ProductVO;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import sun.security.krb5.internal.PAData;

@WebServlet("/productUpdate.do")
public class ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductUpdate() {
        super();
    }

    //페이지이동, selectProductBycode()를 통해 해당 상품에 대한 수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO pDao = ProductDAO.getInstance();		//DAO인스턴스 생성
		String code = request.getParameter("code");		//DAO메소드 에 사용할 매개변수 form에서 가져옴
		ProductVO pVo = pDao.selectProductBycode(code);	//폼에서 가져온 code를 매개로 해당 상품정보 pVo에 담음
		
		request.setAttribute("product", pVo);			//setAttribute로 영역객체에 정보 전달
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		String code = multi.getParameter("code");  //수정 폼에서 받아온 값
		String name = multi.getParameter("name");
		int price = Integer.parseInt(multi.getParameter("price"));
		String picture = multi.getParameter("picture");
		if(picture == null ) {
			picture = multi.getParameter("nonmakeImg");
		}
		String desc = multi.getParameter("description");
		
		
		
		ProductVO pVo = new ProductVO();
		pVo.setCode(Integer.parseInt(code));
		pVo.setDescription(desc);
		pVo.setName(name);
		pVo.setPictureUrl(picture);
		pVo.setPrice(price);
		
		
		ProductDAO pDao = ProductDAO.getInstance();

		pDao.updateProduct(pVo);
		
		response.sendRedirect("productList.do");
	}

}
