package org.wheat.beautyRanking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wheat.beautyRanking.dbHelper.MysqlDBHelper;
import org.wheat.beautyRanking.entity.json.BeautyIntroductionListJson;
import org.wheat.beautyRanking.loader.HttpDataLoaderServer;

public class GetRisePage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetRisePage() {
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
		System.out.println("step in getRisePage servlet------------->");
		/*
		String parameters=request.getParameter("s");
		Map< String, String> map=null;
		try {
			map=HttpDataLoaderServer.dencryptParamsFromGet(parameters);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(map==null)
		{
			System.out.println("map is null");
			return;
		}
		*/
		String firstIndex=request.getParameter("firstIndex");
		String count=request.getParameter("count");
		if(firstIndex==null||count==null)
			return;
		MysqlDBHelper dbHelper=MysqlDBHelper.getInstance();
		//获取上一个星期beauty人气的提升榜
		BeautyIntroductionListJson beautyIntroductionListJson=dbHelper.getRisePage(Integer.parseInt(firstIndex), Integer.parseInt(count),10);
		if(beautyIntroductionListJson==null)
		{
			System.out.println("beautyIntroductionListJson is null");
			return;
		}
		String json=HttpDataLoaderServer.toJson(beautyIntroductionListJson);
		String result=new String(json.getBytes("UTF-8"),"8859_1");
		PrintWriter out=response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return;
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
