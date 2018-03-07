package by.tc.web.dao;

import java.util.HashMap;
import java.util.Map;

import by.tc.web.dao.DOMImpl.DOMDAO;
import by.tc.web.dao.SAXImpl.SAXDAO;
import by.tc.web.dao.StAXImpl.StAXDAO;

public final class DAOFactory {

	private static DAOFactory instance = new DAOFactory();
	
	private Map<String, BookDAO> dao = new HashMap<>();  
	
	private DAOFactory() {
		dao.put("SAX", new SAXDAO());
		dao.put("StAX", new StAXDAO());
		dao.put("DOM", new DOMDAO());
	}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public BookDAO getBookDAO(String parserType) {
		return dao.get(parserType);
	}
}
