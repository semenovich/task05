package by.tc.web.service.impl;

import java.util.ArrayList;

import by.tc.web.dao.BookDAO;
import by.tc.web.dao.DAOException;
import by.tc.web.dao.DAOFactory;
import by.tc.web.entity.Book;
import by.tc.web.entity.Info;
import by.tc.web.service.BookService;
import by.tc.web.service.ServiceException;

public class BookServiceImpl implements BookService {

	@Override
	public Info parse(int page, String parserType) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO(parserType);

		Info info = new Info();
		
		ArrayList<Book> books;
		try {
			books = bookDAO.parse();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		int startIndex = NODE_QUANTITY * (page - 1);
		info.setBooks(getPortion(books, startIndex));
		
		int pagesNumber = (int) Math.ceil((double)(books.size() + 1) / NODE_QUANTITY);
		info.setPagesNumber(pagesNumber);
		
		info.setParserType(parserType);
		return info;
	}
	
	private ArrayList<Book> getPortion(ArrayList<Book> books, int startIndex){
		ArrayList <Book> booksPortion = new ArrayList<Book>();
		for (int i = startIndex; i < startIndex + NODE_QUANTITY && i < books.size(); i++) {
			booksPortion.add(books.get(i));
		}
		return booksPortion;
	}
}
