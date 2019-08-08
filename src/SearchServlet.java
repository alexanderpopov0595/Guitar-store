
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
		// Получение списка продуктов
		/*
		 * ArrayList<Product> products = ProductDB.select(); ArrayList<Product>
		 * products=(ArrayList<Product>) request.getAttribute("products"); // Новый
		 * список, соответствующий запросу ArrayList<Product> productsSelected = new
		 * ArrayList<Product>(); // Фильтрация списка for (Product product : products) {
		 * // Если товар соответствуют критерию поиска if ((product.getName()==null ||
		 * product.getName().equals(name))&& (product.getPrice()==0 ||
		 * product.getPrice()==price)&& (product.getType()==null ||
		 * product.getType().equals("type"))) { // Добавить подходящий товар в новый
		 * список productsSelected.add(product); } }
		 * 
		 * // Помещение списка в аттрибут request.setAttribute("products",
		 * productsSelected); // Вывести сообщение с количеством найденных совпадений
		 * request.setAttribute("SearchResults", productsSelected.size() + " results");
		 */
		// Возвращение обратно на страницу со списком товара
		// getServletContext().getRequestDispatcher("/welcome").forward(request,
		// response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Получение параметров из заполненной формы
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
		// Помещение списка в аттрибут
		request.setAttribute("products", products);
		// Вывести сообщение с количеством найденных совпадений
		request.setAttribute("SearchResults", products.size() + " results");
		// Возвращение обратно на страницу со списком товара
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
