import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Product;
import business.ProductDB;
@WebServlet("/welcome")
public class IndexServlet extends HttpServlet {

	// При get-запросе выводится список всех доступных товаров
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Получение списка продуктов
		ArrayList<Product> products = ProductDB.select();
		// Помещение списка в аттрибут
		request.setAttribute("products", products);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	

}