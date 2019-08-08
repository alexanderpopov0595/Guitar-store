
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

		// ��������� id ���������� ������ ����� ���������
		int product_id = Integer.parseInt(request.getParameter("id"));

		// ���������� ������ � ���������� � ��� ��������� �� ���� ������
		Product product = ProductDB.selectProduct(product_id);

		// �������� �������� � ��������
		request.setAttribute("product", product);
		
		
		//���� �� �������� ������ ������� ��� �������� - � ���� ���������� ����������� ������������� �����
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		//�������� user_id �������� ������������ � ��������� ������
		//���� ������������ ����� � ������� � ��� id ��������� � id ���������
		if(user!=null && user.getId()==product.getUser().getId()) {
			//������� ���������, ������� ����� �������������� ��� ������
			String edit="Edit";
			//��������� � ��������
			request.setAttribute("edit", edit);
		}

		// �������� ������� �������� product.jsp
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

	// � post-����� �������� �� post-������ CreateServlet
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������� product_id
		int product_id = (int) request.getAttribute("product_id");
		// ���������� ������ � ���������� � ��� ��������� �� ���� ������
		Product product = ProductDB.selectProduct(product_id);

		// �������� �������� � ��������
		request.setAttribute("product", product);
		
		//������� ���������, ������� ����� �������������� ��� ������
		String edit="Edit";
		//��������� � ��������
		request.setAttribute("edit", edit);

		// �������� ������� �������� product.jsp
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

}
