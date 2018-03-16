package by.tc.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.web.entity.Info;
import by.tc.web.service.BookService;
import by.tc.web.service.ServiceException;
import by.tc.web.service.ServiceFactory;

public class FrontContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final String PARSER_TYPE = "parserType";
    private static final String PAGE = "page";
    private static final String INFO = "info";
    private static final String RESULT_PAGE = "WEB-INF/jsp/result.jsp";

    public FrontContoller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
			
		String parserType = request.getParameter(PARSER_TYPE);		
		int page = Integer.valueOf(request.getParameter(PAGE));
		
		Info info;
		try {
			info = bookService.parse(page, parserType);
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(), e.getCause());
		}
		
		request.setAttribute(INFO, info);
		request.setAttribute(PAGE, page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(RESULT_PAGE); 
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
