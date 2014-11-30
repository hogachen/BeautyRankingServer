package org.wheat.beautyRanking.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wheat.beautyRanking.loader.HttpDataLoaderServer;

public class DownLoadPhotoServlet extends HttpServlet {
	private static final String AVATAR_DIRECTORY="D:/BeautyRankingPhoto/avatar/";
	/**
	 * Constructor of the object.
	 */
	public DownLoadPhotoServlet() {
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
		String path=request.getParameter("name");
		System.out.println("downloap image----->"+path);
		File file=new File(AVATAR_DIRECTORY+path);
		if(!file.exists()||file.isDirectory())
		{
			//文件不存在或者该路径是目录
			System.out.println("FileNotFullexception------------>");
		}
		FileInputStream fis=new FileInputStream(file);
		byte[] buf = new byte[1024];
		int length=0;
	    ByteArrayOutputStream baos=new ByteArrayOutputStream();
	    while((length=fis.read(buf))!=-1){
	    	baos.write(buf, 0, length);
	    }
	    fis.close();
	        
	    ServletOutputStream sos=response.getOutputStream();
	    sos.write(baos.toByteArray());
	        
	    sos.flush();
	    sos.close();
	    baos.close();
	    System.out.println("downloap image----->"+path+"success!!!");
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
		System.out.println("step in downLoad ------->");
		HashMap<String , String > postData=HttpDataLoaderServer.getParamsFromEntity(request);
		System.out.println(postData.get("path")+"------------>");
		File file=new File(AVATAR_DIRECTORY+postData.get("path"));
		if(!file.exists()||file.isDirectory())
		{
			//文件不存在或者该路径是目录
			System.out.println("FileNotFullexception----------->");
		}
		FileInputStream fis=new FileInputStream(file);
		byte[] buf = new byte[1024];
		int length=0;
	    ByteArrayOutputStream baos=new ByteArrayOutputStream();
	    while((length=fis.read(buf))!=-1){
	    	baos.write(buf, 0, length);
	    }
	    fis.close();
	        
	    ServletOutputStream sos=response.getOutputStream();
	    sos.write(baos.toByteArray());
	        
	    sos.flush();
	    sos.close();
	    baos.close();
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
