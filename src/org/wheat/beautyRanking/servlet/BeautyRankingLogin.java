package org.wheat.beautyRanking.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.wheat.beautyRanking.coders.Coder_Md5;
import org.wheat.beautyRanking.dbHelper.MysqlDBHelper;
import org.wheat.beautyRanking.entity.UserLogin;
import org.wheat.beautyRanking.entity.json.UserLoginJson;
import org.wheat.beautyRanking.loader.HttpDataLoaderServer;

public class BeautyRankingLogin extends HttpServlet 
{

	/**
	 * Constructor of the object.
	 */
	public BeautyRankingLogin() {
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
		System.out.println("post method---------------->");
		InputStream is=request.getInputStream();
		int streamLength=request.getContentLength();
		byte[] data=new byte[streamLength];
		int count=0;
		int len=0;
		while((len=is.read(data, count, streamLength-count))!=-1)
		{
			count=+len;
		}
		HashMap<String, String> postData=null;
		try {
			postData=HttpDataLoaderServer.dencryptParamsFromPost(data);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(postData==null)
		{
			//没收到客户端发来的数据
		}
		MysqlDBHelper helper=MysqlDBHelper.getInstance();
		System.out.println("userPhoneNumber---------->"+postData.get("userPhoneNumber"));
		UserLogin user=helper.selectUser(postData.get("userPhoneNumber"));
		UserLoginJson userJson=new UserLoginJson();
		if(user==null)
		{
			userJson.setCode(0);//0表示不存在该用户
			userJson.setData(null);
		}
		else
		{
			if(postData.get("password").equals(Coder_Md5.md5(user.getPassword())))
			{
				user.setPassword(null);
				userJson.setData(user);
				userJson.setCode(1);//1表示登录成功
			}
			else
			{
				userJson.setCode(-1);//-1表示密码不正确
				userJson.setData(null);
			}
		}
		PrintWriter out=response.getWriter();
		out.print(HttpDataLoaderServer.toJson(userJson));
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
