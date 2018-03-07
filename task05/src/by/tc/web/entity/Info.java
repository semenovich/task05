package by.tc.web.entity;

import java.util.ArrayList;

public final class Info {
	ArrayList<Book> books;
	int pagesNumber;
	String parserType;
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public int getPagesNumber() {
		return pagesNumber;
	}
	
	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	public String getParserType() {
		return parserType;
	}

	public void setParserType(String parserType) {
		this.parserType = parserType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + pagesNumber;
		result = prime * result + ((parserType == null) ? 0 : parserType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Info other = (Info) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (pagesNumber != other.pagesNumber)
			return false;
		if (parserType == null) {
			if (other.parserType != null)
				return false;
		} else if (!parserType.equals(other.parserType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Info [books=" + books + ", pagesNumber=" + pagesNumber + ", parserType=" + parserType + "]";
	}
	
	
}
