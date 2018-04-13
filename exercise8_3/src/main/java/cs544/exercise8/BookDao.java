package cs544.exercise8;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import cs544.sample.NoSuchResourceException;

@Service
public class BookDao implements IBookDao {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);	
	private static int idCount = 1;
	private Map<Integer, Book> books = new HashMap<>();

	public BookDao() throws ParseException {
		add(new Book("Harry and The magic", "11111111ISBN", "James Adam", 100.50, df.parse("2018-01-01")));
		add(new Book("Lucy and The adventure", "22222222ISBN", "Adam Smith", 70.50,  df.parse("2018-02-01")));
	}

	@Override
	public List<Book> getAll() {
		return new ArrayList<Book>(books.values());
	}

	@Override
	public void add(Book book) {
		book.setId(idCount);
		books.put(idCount, book);
		idCount++;
	}

	@Override
	public Book get(int id) {
		Book result = books.get(id);

		if (result == null) {
			throw new NoSuchResourceException("Book", id);
		}

		return result;
	}

	@Override
	public void update(int bookId, Book book) {
		books.put(bookId, book);
	}

	@Override
	public void delete(int bookId) {
		Book removed = books.remove(bookId);
		if (removed == null) {
			throw new NoSuchResourceException("Book", bookId);
		}
	}
}
