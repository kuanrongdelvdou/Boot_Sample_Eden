package com.eden.hao.elsticsearch.bean;

import io.searchbox.annotations.JestId;

public class Article {

	@JestId // 标识这是一个主键
	private Integer id;
	
	private String author;
	
	private String title;
	
	private String content;
	
	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(Integer id, String author, String title, String content) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
