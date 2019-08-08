
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.User;
import business.UserDB;
@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
	// При get-запросе выводим страницу авторизации
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Если уже авторизировался
		// Получение сессии
		HttpSession session = request.getSession();
		// Получение текущего пользователя
		User user = (User) session.getAttribute("user");
		// Если он есть
		if (user != null) {
			// Перенаправление на страницу профиля
			getServletContext().getRequestDispatcher("/profile").forward(request, response);

		}
		// Если еще нет
		else {
			getServletContext().getRequestDispatcher("/authorization.jsp").forward(request, response);
		}

	}

	// При отправке заполненной формы попадаем в post-метод
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Получение логина и пароля
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		//Попытка извлечь пользователя с такими логином и паролем из базы данных
		User user=UserDB.authorization(login, password);
		// Если пользователь с таким логином и паролем есть в базе данных, то метод вернет не nul;
		if (UserDB.authorization(login, password) != null) {
			// получаем сессию
			HttpSession session = request.getSession();
			// Поместить пользователя в аттрибут, чтобы на странице с профилем отобразить
			// его список товаров и личные данные
			session.setAttribute("user", user);
			// Перенаправление на страницу профиля
			getServletContext().getRequestDispatcher("/profile").forward(request, response);

		}
		// Если пользователя с таким логином и паролем нет в базе данных
		else {
			// Сообщение об ошибке ввода логина/пароля
			// Установка сообщения в аттрибут
			request.setAttribute("message", "Incorrect login/password");
			// В случае неудачи идет переадресация обратно jsp страницу
			getServletContext().getRequestDispatcher("/authorization.jsp").forward(request, response);
		}
	}

}
