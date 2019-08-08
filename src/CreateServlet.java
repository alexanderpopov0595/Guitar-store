
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

	// ��� Get-������� ���������� ��������������� �� �������� create.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
	}

	// ��� �������� ����� �������� ������ � ������� �� � ����
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��������� ������������ ������ �� �����
		// �������� ������
		HttpSession session = request.getSession();
		// �������� ������������
		User user = (User) session.getAttribute("user");
		// �������� user_id � ��������� ������
		int user_id = user.getId();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		// ��������� �����������
		Part part = request.getPart("image");
		// ����������� ����� ���� �� ���������. ��� �������� ����� � �������� ��
		// ���������
		String fileName = "default.jpg";
		// ���� ��� ���� ���������
		
		try {
			if (part.getSize()!=0) {
				// ����������� ���� ��������� ��� ���������� ������ -  product_id
				// ������� ����� ����� ������ ��� max(product_id)+1
				 int product_id=ProductDB.getLastId()+1;
				 fileName=product_id+".jpg";
				String absolutePath = request.getServletContext().getRealPath("Images");	
				String savePath = absolutePath + File.separator + fileName;	
				// �������� ����������� � �����
				part.write(savePath);
			}
			
			// �������� ��������
			Product product = new Product(user_id, name, Integer.parseInt(price), type, description,
					"Images/" + fileName);
			// ������� �������� � ���� ������ � ��������� id ������������ ������
			int product_id = ProductDB.insert(product);
			// �������� product_id � ��������, ����� ProductServlet ��� ������� ��� �� ����
			// ������
			request.setAttribute("product_id", product_id);
			// ������� � �������� � ��������� � ��� ����� post
			getServletContext().getRequestDispatcher("/product").forward(request, response);
		} catch (Exception e) {			
			
			// � ������ ������� ���� ������������� ������� �� �������� ���������� ������
			response.sendRedirect(request.getContextPath() + "/create.jsp");
		}

	}

}
