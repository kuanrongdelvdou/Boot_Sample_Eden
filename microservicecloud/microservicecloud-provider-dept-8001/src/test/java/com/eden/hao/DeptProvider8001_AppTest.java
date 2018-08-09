package com.eden.hao;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptProvider8001_AppTest {

	// 将 DataSource 数据源注入进来, 使用 @Autowired 来自动装配
		@Autowired
		DataSource dataSource;
		
		@Test
		public void contextLoads() throws SQLException {
			
			System.out.println("dataSource 的名字是: "  +  dataSource.getClass().getName());
			
			Connection connection = dataSource.getConnection();
			
			System.out.println("获取到的数据库链接是: " + connection);
			connection.close();
		}
	
}
