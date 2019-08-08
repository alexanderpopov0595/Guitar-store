
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Product;
import business.ProductDB;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
		// ��������� ������ ���������
		/*
		 * ArrayList<Product> products = ProductDB.select(); ArrayList<Product>
		 * products=(ArrayList<Product>) request.getAttribute("products"); // �����
		 * ������, ��������������� ������� ArrayList<Product> productsSelected = new
		 * ArrayList<Product>(); // ���������� ������ for (Product product : products) {
		 * // ���� ����� ������������� �������� ������ if ((product.getName()==null ||
		 * product.getName().equals(name))&& (product.getPrice()==0 ||
		 * product.getPrice()==price)&& (product.getType()==null ||
		 * product.getType().equals("type"))) { // �������� ���������� ����� � �����
		 * ������ productsSelected.add(product); } }
		 * 
		 * // ��������� ������ � �������� request.setAttribute("products",
		 * productsSelected); // ������� ��������� � ����������� ��������� ����������
		 * request.setAttribute("SearchResults", productsSelected.size() + " results");
		 */
		// ����������� ������� �� �������� �� ������� ������
		// getServletContext().getRequestDispatcher("/welcome").forward(request,
		// response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������� ���������� �� ����������� �����
		String name = request.getParameter("name");
		String priceParameter=request.getParameter("price");
		int price=0;
		if(priceParameter!=null) {
			 price = Integer.parseInt(request.getParameter("price"));
		}
		else {
			price=0;
		}		
		String type = request.getParameter("type");
		String order = request.getParameter("order");
		ArrayList<Product> products = ProductDB.selectSearch(name, price, type, order);
		// ��������� ������ � ��������
		request.setAttribute("products", products);
		// ������� ��������� � ����������� ��������� ����������
		request.setAttribute("SearchResults", products.size() + " results");
		// ����������� ������� �� �������� �� ������� ������
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
