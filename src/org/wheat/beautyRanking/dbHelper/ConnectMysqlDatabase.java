package org.wheat.beautyRanking.dbHelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMysqlDatabase 
{
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/beauty_ranking?characterEncoding=utf8";
	private String user="root";
	private String password="";
	private Connection conn;
	public Connection ConnectMysql()
	{
		try
		{
			Class.forName(driver);
			conn=(Connection)DriverManager.getConnection(url, user, password);
			if(!conn.isClosed())
			{
				System.out.println("Succeeded connecting to the Database!");
			}
			else
			{
				System.out.println("Failed connecting to the Database!");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return conn;
	}
}
