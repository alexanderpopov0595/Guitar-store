

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.UserDB;


@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Получаем из страницы с редактированием id товара
		int user_id=Integer.parseInt(request.getParameter("id"));
		
		//Удаляем его из базы данных
		UserDB.delete(user_id);
		//Удаляем его из сессии
		HttpSession session=request.getSession();
		//Удаление аттрибута
		session.removeAttribute("user");
		
		//Перенаправляем обратно на страницу профиля
		getServletContext().getRequestDispatcher("/welcome").forward(request, response);
	}


}
