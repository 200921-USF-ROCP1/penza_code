package codesignaltest;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

@com.fasterxml.jackson.annotation.JsonPropertyOrder({"id", "title"})
	class Book {
	  private int id;
	  private String title;

	  Book(int id, String title) {
	    this.id = id;
	    this.title = title;
	  }

	  @com.fasterxml.jackson.annotation.JsonGetter
	  public int getId() {
	      return this.id;
	  }

	  @com.fasterxml.jackson.annotation.JsonGetter
	  public String getTitle() {
	      return this.title;
	  }
	  
	  public void setTitle(String title) {
		  this.title = title;
	  }
	}

	class BookManager {
	  private ArrayList<Book> books;

	  BookManager() {
	    this.books = new ArrayList<Book>();
	  }

	  boolean createBook(int id, String title) {
	    //if( books.c)
        System.out.println( "in create book");

	    Book book = new Book(id, title);
	    books.add(book);
        System.out.println( "end create book");
	    return true;
	  }

	  boolean updateBook(int id, String title) {
	    // TODO: return false if the book id does not exist
	    
	    Book book = findBookById(id);
	    if (book != null) {
	      book.setTitle(title);
	      return true;
	    } else {
	      return false;
	    }

	  }

	  boolean deleteBook(int id) {
	    for (int i = 0; i < books.size(); i++) {
	      Book curBook = books.get(i);
	      if (curBook.getId() == id) {
	        books.remove(i);
	        return true;
	      }
	    }
	    return false;
	  }

	  Book findBookById(int id) {
	    // book or null
	    for (int i = 0; i < books.size(); i++) {
	      Book curBook = books.get(i);
	      if (curBook.getId() == id) {
	        return curBook;
	      }
	    }
	    return null;
	  }

	  Book findBookByTitle(String title) {
	    // book or null
	    for (int i = 0; i < books.size(); i++) {
	      Book curBook = books.get(i);
	      if (curBook.getTitle().equals(title)) {
	        return curBook;
	      }
	    }
	    return null;
	  }

	// Do NOT edit the code below this comment.
	// You should be able to complete this test without editing below this comment.

	BookManager bookManager = new BookManager();

	ArrayList<String> bookManagementRefactor(String[][] operations) {
	  // Calls corresponding methods of bookManager based on the input
	  ArrayList<String> answer = new ArrayList<>();
	  com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
	  for (int i = 0; i < operations.length; i++) {
	    try {
	      String[] operation = operations[i];
	      switch (operation[0]) {
	        case "createBook": {
                System.out.println( "creating book");
	          int id = Integer.parseInt(operation[1]);
	          answer.add(mapper.writeValueAsString(bookManager.createBook(id, operation[2])));
	          break;
	        }
	        case "updateBook": {
	          int id = Integer.parseInt(operation[1]);
	          answer.add(mapper.writeValueAsString(bookManager.updateBook(id, operation[2])));
	          break;
	        }
	        case "deleteBook": {
	          int id = Integer.parseInt(operation[1]);
	          answer.add(mapper.writeValueAsString(bookManager.deleteBook(id)));
	          break;
	        }
	        case "findBookById": {
	          int id = Integer.parseInt(operation[1]);
	          Book book = bookManager.findBookById(id);
	          answer.add(mapper.writeValueAsString(book));
	          break;
	        }
	        case "findBookByTitle": {
	          String title = operation[1];
	          Book book = bookManager.findBookByTitle(title);
	          answer.add(mapper.writeValueAsString(book));
	          break;
	        }
	      }
	    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
	      System.out.println(e);
	    }
	  }
	  return answer;
	}
}