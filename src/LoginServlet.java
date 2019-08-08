
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.ProductDB;
import business.User;
import business.UserDB;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ѕри get-запросе вывести форму дл€ заполнени€ данных о пользователе
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ѕолучение даннных из запроса
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String telephone = request.getParameter("telephone");
		String login = request.getParameter("login");
		String password = request.getParameter("password");		
		
		try {
			//—оздание пользовател€ по данным из формы
			User user=new User(firstName, lastName, telephone, login, password);
			// «анесение пользовател€ в базу и получение id пользовател€
			int user_id=UserDB.insert(user);
			//—оздание пользовател€ с user_id, чтобы отобразить весь его товар, и с именем и фамилией дл€ вывода приветстви€
			user=new User(user_id, firstName, lastName, telephone, login, password);
			// получаем сессию
	        HttpSession session = request.getSession();
			//ѕоместить пользовател€ в аттрибут, чтобы на странице с профилем отобразить его список товаров и личные данные
			session.setAttribute("user", user);			
			// ѕеренаправление на страницу профил€
			getServletContext().getRequestDispatcher("/profile").forward(request, response);
		}
		// ¬ случае ошибки
		catch (Exception e) {
			// ”становить сообщение в аттрибут
			request.setAttribute("message", "This login/password is already used!");
			// ¬ернутьс€ обратно на страницу логина
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}
