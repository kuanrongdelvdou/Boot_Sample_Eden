package com.eden.hao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql8Test {

	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        String user="root";
        String password="root";
        String url="jdbc:mysql://192.168.0.107:3306/cloudDB01?useSSL=false&serverTimezone=UTC";//mydb为Mysql数据库中创建的数据库实例名
        String driver="com.mysql.cj.jdbc.Driver";    
        
        String tableName="dept";//studinfo为数据库mydb中的表名
        String sqlstr;
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        
        try
        {
            Class.forName(driver);
            con=DriverManager.getConnection(url, user, password);
            stmt=con.createStatement();        
            
      
            sqlstr="select * from "+ tableName;
            rs=stmt.executeQuery(sqlstr);
            
            ResultSetMetaData rsmd=rs.getMetaData();
            int j=0;
            j=rsmd.getColumnCount();
            for(int k=0;k<j;k++)
            {
                System.out.print(rsmd.getColumnName(k+1));
                System.out.print("\t");
            }
            
            System.out.println();
            
            while(rs.next())
            {
                for(int i=0;i<j;i++)
                {
                    System.out.print(rs.getString(i+1));
                    System.out.print("\t");
                }        
                System.out.println();
            }                
        }
        catch(ClassNotFoundException e1)
        {
            System.out.print("数据库驱动不存在！");
            System.out.print(e1.toString());
        }
        catch(SQLException e2)
        {
            System.out.print("数据库存在异常！");
            System.out.print(e2.toString());
        }
        finally
        {
            try
            {
                if(rs!=null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if(con!=null)
                    con.close();    
            }
            catch(SQLException e)
            {
                System.out.print(e.toString());
            }
        } 
    }
}
