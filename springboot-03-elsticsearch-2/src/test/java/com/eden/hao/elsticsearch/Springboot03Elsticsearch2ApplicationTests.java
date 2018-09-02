package com.eden.hao.elsticsearch;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eden.hao.elsticsearch.bean.Article;
import com.eden.hao.elsticsearch.bean.Book;
import com.eden.hao.elsticsearch.repository.BookRepository;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03Elsticsearch2ApplicationTests {

	@Autowired
	JestClient jestClient;
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	public void test02(){
		
//		Book book = new Book();
//		book.setAuthor("你大爷");
//		book.setBookName("西游记");
//		book.setId(1);
//		bookRepository.index(book);
		
		for(Book book : bookRepository.findByBookNameLike("西")){
			System.out.println("*****"  + book + "**********");
		}
	}
	
	
	@Test
	public void contextLoads() {
		
		//1. 给 ES 中所以(保存)一个文档.
		Article article = new Article();
		article.setId(1);
		article.setAuthor("郝伟");
		article.setTitle("好消息");
		article.setContent("清仓大甩卖");
		
		Index index = new Index.Builder(article).index("eden").type("news").build();
		
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 测试搜索
	@Test
	public void search(){
		
		// 查询表达式
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"仓\"\n" +
				"        }\n" +
				"    }\n" +
				"}";
		
		// 构建搜索功能
		Search search = new Search.Builder(json).addIndex("eden").addType("news").build();
		
		try {
			SearchResult result = jestClient.execute(search);
			
			System.out.println("++++++++++++++++++++++++" + result.getJsonString() + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
