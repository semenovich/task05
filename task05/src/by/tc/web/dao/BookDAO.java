package by.tc.web.dao;

import java.util.ArrayList;

import by.tc.web.entity.Book;

public interface BookDAO {
	ArrayList<Book> parse() throws DAOException;
}
