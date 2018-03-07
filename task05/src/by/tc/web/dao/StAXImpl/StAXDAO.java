package by.tc.web.dao.StAXImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import by.tc.web.dao.BookDAO;
import by.tc.web.dao.DAOException;
import by.tc.web.entity.Book;
import by.tc.web.entity.Genre;
import by.tc.web.entity.SourceName;
import by.tc.web.entity.Tag;

public class StAXDAO implements BookDAO {

	@Override
	public ArrayList<Book> parse() throws DAOException {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = null;
		ArrayList<Book> books = null;
		try {
			xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(SourceName.get()));
			books = getBooks(xmlEventReader);
		} catch (FileNotFoundException | XMLStreamException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return books;
	}

	private ArrayList<Book> getBooks(XMLEventReader xmlEventReader) throws XMLStreamException {
		ArrayList<Book> books = new ArrayList<>();
		Book currentBook = null;
		while (xmlEventReader.hasNext()) {
			XMLEvent xmlEvent = xmlEventReader.nextEvent();
			if (xmlEvent.isStartElement()) {
				StartElement startElement = xmlEvent.asStartElement();
				switch (Tag.valueOf(startElement.getName().getLocalPart())) {
					case book:
						currentBook = new Book();
						Attribute idAttr = startElement.getAttributeByName(new QName("id"));
						currentBook.setId(idAttr.getValue());
						break;
					case title:
						xmlEvent = xmlEventReader.nextEvent();
						currentBook.setTitle(xmlEvent.asCharacters().getData());
						break;
					case author:
						xmlEvent = xmlEventReader.nextEvent();
						currentBook.setAuthor(xmlEvent.asCharacters().getData());
						break;
					case genre:
						xmlEvent = xmlEventReader.nextEvent();
						currentBook.setGenre(Genre.valueOf(xmlEvent.asCharacters().getData()));
						break;
					case price:
						xmlEvent = xmlEventReader.nextEvent();
						currentBook.setPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
						break;
					case publish_date:
						xmlEvent = xmlEventReader.nextEvent();
						currentBook.setPublishDate(xmlEvent.asCharacters().getData());
						break;
					default:
						break;	
				}
			}
			if (xmlEvent.isEndElement()) {
				EndElement endElement = xmlEvent.asEndElement();
				if (Tag.valueOf(endElement.getName().getLocalPart()) == Tag.book) {
					books.add(currentBook);
				}
			}
		}
		return books;
	}
}
