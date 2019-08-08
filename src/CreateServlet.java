
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import business.Product;
import business.ProductDB;
import business.User;

@WebServlet("/create")
@MultipartConfig
public class CreateServlet extends HttpServlet {

	// При Get-запросе происходит перенаправление на страницу create.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
	}

	// При отправке формы получает данные и заносит их в базу
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Получение отправленных данных из формы
		// Получаем сессию
		HttpSession session = request.getSession();
		// Получаем пользователя
		User user = (User) session.getAttribute("user");
		// Получаем user_id и остальные данные
		int user_id = user.getId();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		// Получение изображения
		Part part = request.getPart("image");
		// Изображение может быть не загружено. Это название файла с картиной по
		// умолчанию
		String fileName = "default.jpg";
		// Если оно было загружено
		
		try {
			if (part.getSize()!=0) {
				// Изображение надо сохранить под уникальным именем -  product_id
				// Получим номер этого товара как max(product_id)+1
				 int product_id=ProductDB.getLastId()+1;
				 fileName=product_id+".jpg";
				String absolutePath = request.getServletContext().getRealPath("Images");	
				String savePath = absolutePath + File.separator + fileName;	
				// Сохраним изображение в папку
				part.write(savePath);
			}
			
			// Создание продукта
			Product product = new Product(user_id, name, Integer.parseInt(price), type, description,
					"Images/" + fileName);
			// Вставка продукта в базу данных и получение id вставленного товара
			int product_id = ProductDB.insert(product);
			// Поместим product_id в аттрибут, чтобы ProductServlet мог извлечь его из базы
			// данных
			request.setAttribute("product_id", product_id);
			// Переход к сервлету с продуктом в его метод post
			getServletContext().getRequestDispatcher("/product").forward(request, response);
		} catch (Exception e) {			
			
			// В случае неудачи идет переадресация обратно на страницу добавления товара
			response.sendRedirect(request.getContextPath() + "/create.jsp");
		}

	}

}
