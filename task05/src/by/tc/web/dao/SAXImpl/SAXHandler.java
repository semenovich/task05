package by.tc.web.dao.SAXImpl;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.tc.web.entity.Book;
import by.tc.web.entity.Genre;
import by.tc.web.entity.Tag;

public final class SAXHandler extends DefaultHandler{

    private static final String BOOK_ID = "id";
    private static final String OPEN_TAG = "<";

	private ArrayList<Book> books;
	private Book currentBook;
	private String currentElement;
	
	public ArrayList<Book> getBooks(){
		return books;
	}
	
	@Override
	public void startDocument() throws SAXException {
		return;
	}

	@Override
	public void endDocument() throws SAXException {
		return;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = qName;
		
		switch (Tag.valueOf(currentElement)) {
		case catalog:
			books = new ArrayList<Book>();
			break;
		case book:
			currentBook = new Book();
			currentBook.setId(attributes.getValue(BOOK_ID));
			break;
		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(Tag.valueOf(qName)) {
		case book:
			books.add(currentBook);
			currentBook = null;
			break;
		default:
			break;
		}
		currentElement = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		if (text.contains(OPEN_TAG) || currentElement == null) {
			return;
		}
		switch (Tag.valueOf(currentElement)) {
		case title:
			currentBook.setTitle(text);
			break;
		case author:
			currentBook.setAuthor(text);
			break;
		case genre:
			currentBook.setGenre(Genre.valueOf(text));
			break;
		case price:
			currentBook.setPrice(Double.parseDouble(text));
			break;
		case publish_date:
			currentBook.setPublishDate(text);
			break;
		default:
			break;
		}
	}

}
