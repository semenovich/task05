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
       
    public FrontContoller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
			
		String parserType = request.getParameter("parserType");		
		int page = Integer.valueOf(request.getParameter("page"));
		
		Info info;
		try {
			info = bookService.parse(page, parserType);
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(), e.getCause());
		}
		
		request.setAttribute("info", info);
		request.setAttribute("page", page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/result.jsp"); 
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
