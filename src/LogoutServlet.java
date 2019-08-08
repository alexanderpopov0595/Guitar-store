

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Получение сессии
		HttpSession session=request.getSession();
		//Удаление аттрибута
		session.removeAttribute("user");
		//Перенаправить на начальный сервлет
		getServletContext().getRequestDispatcher("/welcome").forward(request, response);
	}

	

}
