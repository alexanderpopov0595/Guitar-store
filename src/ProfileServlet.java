
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.ProductDB;
import business.User;
import business.Product;
import business.ProductDB;
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// получаем сессию
        HttpSession session = request.getSession();
		// Получение пользователя
		User user=(User) session.getAttribute("user");	
		
		// Получение списка продукта пользователя через user_id
		ArrayList<Product> products = ProductDB.select(user.getId());
		
		
		//Помещение в аттрибуты сообщения, сколько у этого пользователя всего товара
		//Если товар есть - вывести его количество
		if(products.size()!=0) {			
			request.setAttribute("message", "You've got "+products.size()+" items.");
		}
		//Если нет - вывести сообщение "Нет товара"
		else {
			request.setAttribute("message", "You've got no items.");	
		}
		
		// Помещение списка товара в аттрибуты
		request.setAttribute("products", products);		
		
		// Перенаправление на страницу профиля
		request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
	}
	//Переадресация на этот сервлет идет из post-метода предыдущего сервлета, поэтому здесь вызывается doGet метод
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
