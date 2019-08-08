
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.Product;
import business.ProductDB;
import business.User;
import business.UserDB;
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Получение id выбранного товара через параметра
		int product_id = Integer.parseInt(request.getParameter("id"));

		// Извлечение товара и информации о его владельце из базы данных
		Product product = ProductDB.selectProduct(product_id);

		// Передача продукта в аттрибут
		request.setAttribute("product", product);
		
		
		//Если на страницу товара перешел его владелец - у него появляется возможность редактировать товар
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		//Сравнить user_id текущего пользователя и владельца товара
		//Если пользователь вошел в акканут и его id совпадает с id владельца
		if(user!=null && user.getId()==product.getUser().getId()) {
			//Создать сообщение, которое будет использоваться как ссылка
			String edit="Edit";
			//Поместить в аттрибут
			request.setAttribute("edit", edit);
		}

		// Передача запроса странице product.jsp
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

	// В post-метод попадаем из post-метода CreateServlet
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Получаем product_id
		int product_id = (int) request.getAttribute("product_id");
		// Извлечение товара и информации о его владельце из базы данных
		Product product = ProductDB.selectProduct(product_id);

		// Передача продукта в аттрибут
		request.setAttribute("product", product);
		
		//Создать сообщение, которое будет использоваться как ссылка
		String edit="Edit";
		//Поместить в аттрибут
		request.setAttribute("edit", edit);

		// Передача запроса странице product.jsp
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

}
