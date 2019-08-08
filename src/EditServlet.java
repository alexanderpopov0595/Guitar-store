

import java.io.File;
import java.io.IOException;
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
@WebServlet("/edit")
@MultipartConfig
public class EditServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Получение id того товара, чья ссылка edit была нажата
		int product_id=Integer.parseInt(request.getParameter("id"));
		//Получение соответствующего продукта из базы данных
		Product product=ProductDB.selectProduct(product_id);		
		//Поместить продукт в аттрибут
		request.setAttribute("product", product);
		//Перенаправить на страницу edit.jsp, чтобы перейти на страницу товара
		getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Получение данных из формы
		int product_id=Integer.parseInt(request.getParameter("id"));	
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		String type=request.getParameter("type");
		String description=request.getParameter("description");
		// Получение изображения
		Part part = request.getPart("image");
		String imagePath=request.getParameter("imagePath");
		//Если загрузили новое фото
		if (part.getSize()!=0) {
			String fileName=product_id+".jpg";		
			String absolutePath = request.getServletContext().getRealPath("Images");
			String savePath = absolutePath + File.separator + fileName;	
			// Сохраним изображение в папку
			part.write(savePath);
		
			imagePath="Images/"+fileName;
		}		
		
		//Создание товара с такими значениями
		Product product=new Product(product_id, user_id,name, price, type, description, imagePath);
		
		//Занесение изменений в базу данных
		ProductDB.update(product);
		
		//Поместим product_id в аттрибут, чтобы ProductServlet мог извлечь его из базы данных
		request.setAttribute("product_id", product_id);
		// Переход к сервлету с продуктом в его метод post
		getServletContext().getRequestDispatcher("/product").forward(request, response);
		
		
	}

}
