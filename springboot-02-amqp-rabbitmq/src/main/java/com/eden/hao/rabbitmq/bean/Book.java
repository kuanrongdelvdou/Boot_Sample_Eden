package com.eden.hao.rabbitmq.bean;

public class Book {

	private String bookName;
	
	private String author;
	
	private Integer count;

	public Book() {
		// TODO Auto-generated constructor stub
	}	
	
	public Book(String bookName, String author, Integer count) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.count = count;
	}



	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", author=" + author + ", count=" + count + "]";
	}
	
	
}
