package Configuration;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigParser {
	//Путь к XML-файлу с конфигурацией
	File file;
	//Получение фабрики, чтобы после получить builder
	DocumentBuilderFactory factory;
	//Получение из фабрики builder
	DocumentBuilder builder;
	//Создание структуры DOM
	Document document;
	//Получение корневого элемента
	NodeList root;
	public ConfigParser(){	
		file=new File("Resources/DatabaseConfiguration.xml");
		factory=DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			document = builder.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUsername() {	
		root=document.getElementsByTagName("username");		
		return root.item(0).getTextContent();
	}
	public String getUserpassword() {
		root=document.getElementsByTagName("userpassword");
		return root.item(0).getTextContent();
	}
	public String getURL() {
		return document.getElementsByTagName("url").item(0).getTextContent()+
				"?serverTimezone="+document.getElementsByTagName("serverTimezone").item(0).getTextContent()+
				"&useSSL="+document.getElementsByTagName("useSSL").item(0).getTextContent();
	}
	
}
