package org.wheat.beautyRanking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wheat.beautyRanking.dbHelper.MysqlDBHelper;
import org.wheat.beautyRanking.entity.json.UserRegisterJson;
import org.wheat.beautyRanking.loader.HttpDataLoaderServer;

public class RegisterUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterUserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String , String > postData=HttpDataLoaderServer.getParamsFromEntity(request);
		if(postData==null)
		{
			//没收到客户端发来的数据
		}
		MysqlDBHelper helper=MysqlDBHelper.getInstance();
		System.out.println("userPhoneNumber---------->"+postData.get("userPhoneNumber"));
		System.out.println("password----------------->"+postData.get("password"));
		System.out.println("nikeName----------------->"+postData.get("nikeName"));
		System.out.println("school------------------->"+postData.get("school"));
		System.out.println("admissionYear------------>"+postData.get("admissionYear"));
		boolean isSuccess=helper.insertUser(postData);
		UserRegisterJson json=new UserRegisterJson();
		if(isSuccess)
		{
			json.setCode(1);
		}
		else
		{
			json.setCode(0);
		}
		PrintWriter out=response.getWriter();
		out.print(HttpDataLoaderServer.toJson(json));
		out.flush();
		out.close();
		return;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
