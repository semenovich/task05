package by.tc.web.dao.DOMImpl;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.tc.web.dao.BookDAO;
import by.tc.web.dao.DAOException;
import by.tc.web.entity.Book;
import by.tc.web.entity.Genre;
import by.tc.web.entity.SourceName;
import by.tc.web.entity.Tag;

public class DOMDAO implements BookDAO {

    private static final String BOOK = "book";

	@Override
	public ArrayList<Book> parse() throws DAOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(SourceName.get());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		
		NodeList bookNodeList = document.getElementsByTagName(BOOK);
		return getBooks(bookNodeList);
	}
	
	private ArrayList<Book> getBooks(NodeList bookList){
		ArrayList<Book> books = new ArrayList<Book>();
		for (int i = 0; i < bookList.getLength(); i++) {
			if (bookList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element bookElement = (Element) bookList.item(i);
				
				Book book = new Book();
				book.setId(bookElement.getAttribute("id"));
				
				NodeList childNodes = bookElement.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Element childElement = (Element) childNodes.item(j);
						
						switch(Tag.valueOf(childElement.getNodeName())) {
						case title:
							book.setTitle(childElement.getTextContent());
							break;
						case author:
							book.setAuthor(childElement.getTextContent());
							break;
						case genre:
							book.setGenre(Genre.valueOf(childElement.getTextContent()));
							break;
						case price:
							book.setPrice(Double.parseDouble(childElement.getTextContent()));
							break;
						case publish_date:
							book.setPublishDate(childElement.getTextContent());
							break;
						default:
							break;
						}
					}
				}
				books.add(book);
			}
		}
		return books;
	}

}
