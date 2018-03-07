package by.tc.web.service;

import by.tc.web.entity.Info;

public interface BookService {
	final int NODE_QUANTITY = 4;
	Info parse(int page, String parserType) throws ServiceException;
}
