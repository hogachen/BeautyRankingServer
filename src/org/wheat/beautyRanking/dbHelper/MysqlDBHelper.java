package org.wheat.beautyRanking.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.wheat.beautyRanking.entity.Beauty;
import org.wheat.beautyRanking.entity.BeautyIntroduction;
import org.wheat.beautyRanking.entity.BeautyIntroductionList;
import org.wheat.beautyRanking.entity.Comment;
import org.wheat.beautyRanking.entity.Photo;
import org.wheat.beautyRanking.entity.PraiseRecord;
import org.wheat.beautyRanking.entity.RequestRegisterUser;
import org.wheat.beautyRanking.entity.UserLogin;
import org.wheat.beautyRanking.entity.json.BeautyIntroductionListJson;


public class MysqlDBHelper
{
	private static MysqlDBHelper dbHelper=new MysqlDBHelper();
	private MysqlDatabase database=new MysqlDatabase();
	private MysqlDBHelper(){};
	public static MysqlDBHelper getInstance()
	{
		return dbHelper;
	}
	public UserLogin selectUser(String userPhoneNumber)
	{
		Connection conn=null;
		UserLogin user=new UserLogin();
		try{
			conn=database.getConnetion();
			String querySql="select * from user_table where user_phone_number="+userPhoneNumber;
			PreparedStatement ps=conn.prepareStatement(querySql);
			ResultSet rs=ps.executeQuery(querySql);
			if(rs.next())
			{
				user.setUserPhoneNumber(rs.getString("user_phone_number"));
				user.setPassword(rs.getString("password"));
				user.setNikeName(rs.getString("nikename"));
				user.setSchool(rs.getString("school"));
				user.setAdmissionYear(rs.getInt("admission_year"));
				user.setSex(rs.getString("sex"));
			}
			else
				return null;
			ps.close();
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return user;
	}
	public boolean insertUser(HashMap<String, String> user)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into user_table(user_phone_number,password,nikename,school,admission_year,sex) values(?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setString(1, user.get("userPhoneNumber"));
			ps.setString(2, user.get("password"));
			ps.setString(3, user.get("nikeName"));
			ps.setString(4, user.get("school"));
			ps.setInt(5, Integer.parseInt(user.get("admissionYear")));
			ps.setString(6, user.get("sex"));
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public boolean insertUserTable(RequestRegisterUser user)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into user_table(user_phone_number,password,nikename,school,admission_year,sex,avatar_path) values(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setString(1, user.getUserPhoneNumber());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickName());
			ps.setString(4, user.getSchool());
			ps.setInt(5, user.getAdmissionYear());
			ps.setString(6, user.getSex());
			ps.setString(7, user.getAvatarPath());
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public boolean insertBeauty(Beauty beauty)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into beauty(beauty_id,avatar_path,true_name,user_phone_number,prase_times,school,admission_year,birthday,constellation,description) values(?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setInt(1, beauty.getBeautyID());
			ps.setString(2, beauty.getAvatarPath());
			ps.setString(3, beauty.getTrueName());
			ps.setString(4, beauty.getUserPhoneNumber());
			ps.setLong(5, beauty.getPraseTimes());
			ps.setString(6, beauty.getSchool());
			ps.setString(7, beauty.getAdmissionYear());
			ps.setString(8, beauty.getBirthday());
			ps.setString(9, beauty.getConstellation());
			ps.setString(10, beauty.getDescription());
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public boolean insertPhoto(Photo photo)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into photo(photo_id,beauty_id,photo_path,user_phone_number,upload_time) values(?,?,?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setInt(1, photo.getPhotoID());
			ps.setInt(2, photo.getBeautyID());
			ps.setString(3, photo.getPhotoPath());
			ps.setString(4, photo.getUserPhoneNumber());
			ps.setString(5, photo.getUploadTime());
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public boolean insertCommentRecord(Comment comment)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into comment_record(photo_id,beauty_id,user_phone_number,comment_time,comment_content) values(?,?,?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setInt(1, comment.getPhotoID());
			ps.setInt(2, comment.getBeautyID());
			ps.setString(3, comment.getUserPhoneNumber());
			ps.setString(4, comment.getCommentTime());
			ps.setString(5, comment.getCommentContent());
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public boolean insertPraiseRecord(PraiseRecord praiseRecord)
	{
		Connection conn=null;
		try
		{
			conn=database.getConnetion();
			String insql="insert into praise_record(user_phone_number,beauty_id,praise_time) values(?,?,?)";
			java.sql.PreparedStatement ps=conn.prepareStatement(insql);
			ps.setString(1, praiseRecord.getUserPhoneNumber());
			ps.setInt(2, praiseRecord.getBeauty());
			ps.setString(3, praiseRecord.getPraiseTime());
			int resutl=ps.executeUpdate();
			if(resutl>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try 
				{
					conn.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	//返回值0代表用户名不存在，1表示密码不正确，2表示用户名和密码都正确
	public int isUserExist(String userPhoneNumber,String userPassword)
	{
		Connection conn=null;
		int returnCode=0;
		try{
			conn=database.getConnetion();
			String sql="selct * from user_table where user_phone_number='"+userPhoneNumber+"'";
			java.sql.Statement statement=conn.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			if(rs.next())
			{
				if(userPassword.equals(rs.getString("password")))
					returnCode=2;
				else 
					returnCode=1;
			}
			else
				returnCode=0;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnCode;
	}
	
	/**
	 * 查找新人榜的榜单
	 * @param firstIndex 起始记录
	 * @param count 需要查询记录的数目
	 * @return
	 */
	public BeautyIntroductionListJson getNewPage(int firstIndex,int count)
	{
		Connection conn=null;
		ArrayList<BeautyIntroduction> list=new ArrayList<BeautyIntroduction>();
		BeautyIntroductionList beautyList;
		BeautyIntroductionListJson beautyIntroductionListJson=null;
		try
		{
			conn=database.getConnetion();
			String querySql="select beauty_id , true_name, school , description ,avatar_path from beauty order by create_time desc limit ?,?";
			PreparedStatement ps=conn.prepareStatement(querySql);
			ps.setInt(1, firstIndex);
			ps.setInt(2, count);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BeautyIntroduction temp=new BeautyIntroduction();
				temp.setBeautyName(rs.getString("true_name"));
				temp.setSchool(rs.getString("school"));
				temp.setDescription(rs.getString("description"));
				temp.setAvatarPath(rs.getString("avatar_path"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(list.size()>0)
		{
			beautyList=new BeautyIntroductionList();
			beautyList.setIntroductionList(list);
			beautyIntroductionListJson=new BeautyIntroductionListJson();
			beautyIntroductionListJson.setData(beautyList);
		}
		return beautyIntroductionListJson;
	}
	
	/**
	 * 查找提升榜的榜单
	 * @param firstIndex
	 * @param count
	 * @param before 前before天的上升数据
	 * @return
	 */
	public BeautyIntroductionListJson getRisePage(int firstIndex,int count,int before)
	{
		Connection conn=null;
		ArrayList<BeautyIntroduction> list=new ArrayList<BeautyIntroduction>();
		BeautyIntroductionList beautyList;
		BeautyIntroductionListJson beautyIntroductionListJson=null;
		try
		{
			conn=database.getConnetion();
			String querySql="select beauty_id , true_name , school , description , avatar_path from beauty where beauty_id in ( select beauty_id from (select beauty_id ,count(*) as count_for_weeks from praise_record where praise_time > ? group by beauty_id order by count_for_weeks desc limit ?,?) as temp)";
			PreparedStatement ps=conn.prepareStatement(querySql);
			ps.setString(1, getDateBefore(before));
			ps.setInt(2, firstIndex);
			ps.setInt(3, count);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BeautyIntroduction temp=new BeautyIntroduction();
				System.out.println(rs.getString("true_name"));
				temp.setBeautyName(rs.getString("true_name"));
				temp.setSchool(rs.getString("school"));
				temp.setDescription(rs.getString("description"));
				temp.setAvatarPath(rs.getString("avatar_path"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(list.size()>0)
		{
			beautyList=new BeautyIntroductionList();
			beautyList.setIntroductionList(list);
			beautyIntroductionListJson=new BeautyIntroductionListJson();
			beautyIntroductionListJson.setData(beautyList);
		}
		return beautyIntroductionListJson;
	}
	private String getDateBefore(int nums)
	{
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -nums);
        return f.format(c.getTime());
	}
	
	/**
	 * 查找总榜的榜单
	 * @param firstIndex
	 * @param count
	 * @return
	 */
	public BeautyIntroductionListJson getSumPage(int firstIndex,int count)
	{
		Connection conn=null;
		ArrayList<BeautyIntroduction> list=new ArrayList<BeautyIntroduction>();
		BeautyIntroductionList beautyList;
		BeautyIntroductionListJson beautyIntroductionListJson=null;
		try
		{
			conn=database.getConnetion();
			String querySql="select beauty_id , true_name ,school, description , avatar_path from beauty order by prase_times desc limit ?,?";
			PreparedStatement ps=conn.prepareStatement(querySql);
			ps.setInt(1, firstIndex);
			ps.setInt(2, count);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BeautyIntroduction temp=new BeautyIntroduction();
				temp.setBeautyName(rs.getString("true_name"));
				temp.setSchool(rs.getString("school"));
				temp.setDescription(rs.getString("description"));
				temp.setAvatarPath(rs.getString("avatar_path"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(list.size()>0)
		{
			beautyList=new BeautyIntroductionList();
			beautyList.setIntroductionList(list);
			beautyIntroductionListJson=new BeautyIntroductionListJson();
			beautyIntroductionListJson.setData(beautyList);
		}
		return beautyIntroductionListJson;
	}
}
