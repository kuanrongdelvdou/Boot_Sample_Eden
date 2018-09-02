package com.eden.hao.elsticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;import org.springframework.social.facebook.api.BookActions;

import com.eden.hao.elsticsearch.bean.Book;

public interface BookRepository extends ElasticsearchRepository<Book, Integer>{
	
	public List<Book> findByBookNameLike(String bookName);

}
