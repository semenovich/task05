package by.tc.web.dao.SAXImpl;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import by.tc.web.dao.BookDAO;
import by.tc.web.dao.DAOException;
import by.tc.web.entity.Book;
import by.tc.web.entity.SourceName;

public class SAXDAO implements BookDAO {

	@Override
	public ArrayList<Book> parse() throws DAOException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXHandler saxHandler = new SAXHandler();
		try {
			SAXParser saxParser = parserFactory.newSAXParser();
			saxParser.parse(SourceName.get(), saxHandler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		} 
		return saxHandler.getBooks();
	}
}
