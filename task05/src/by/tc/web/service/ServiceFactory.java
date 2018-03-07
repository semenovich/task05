package by.tc.web.service;

import by.tc.web.service.impl.BookServiceImpl;

public final class ServiceFactory {
	
	private static ServiceFactory instance = new ServiceFactory();
	
	private final BookService bookService = new BookServiceImpl();
	
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public BookService getBookService() {
		return bookService;
	}
}
