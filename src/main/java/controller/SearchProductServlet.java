package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.HangHoa;
import model.bo.SearchProductBO;

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		
//		String searchText = request.getParameter("searchText");
//		SearchProductBO searchProductBO = new SearchProductBO();
//		ArrayList<HangHoa> dsHangHoa = searchProductBO.getDsHangHoa(searchText);
//		
//		request.setAttribute("dsHangHoa", dsHangHoa);
//		RequestDispatcher rd = request.getRequestDispatcher("productList.jsp");
//		rd.forward(request, response);
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        String searchText = request.getParameter("searchText");
        if (searchText == null) {
            searchText = (String)session.getAttribute("searchProductText");
        }
        SearchProductBO searchProductBO = new SearchProductBO();

        System.out.println("searchText=" + searchText);

        String page = request.getParameter("page");
        int pageNumber = 1; // Mặc định là trang 1, trang đầu tiên
        if (page != null && !"".equals(page)) {
                pageNumber = Integer.valueOf(page);
        }

        ArrayList<HangHoa> dsHangHoa = searchProductBO.getDsHangHoa(searchText, pageNumber);
        int totalPageNumber = searchProductBO.getTotalPageNumber(searchText);

        request.setAttribute("dsHangHoa", dsHangHoa);
        request.setAttribute("currentPageNumber", pageNumber);
        request.setAttribute("totalPageNumber", totalPageNumber);
        
        
        session.setAttribute("searchProductText", searchText);

        RequestDispatcher rd = request.getRequestDispatcher("productList.jsp");

        rd.forward(request, response);
		
	}

}
