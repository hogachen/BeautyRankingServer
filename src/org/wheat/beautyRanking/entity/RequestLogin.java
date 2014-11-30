package org.wheat.beautyRanking.entity;

import org.wheat.beautyRanking.data.DataType;
import org.wheat.beautyRanking.data.IData;

public class RequestLogin implements IData
{
	private static final long serialVersionUID = 1L;
	private int dataType=DataType.REQUEST_LOGIN;
	private String userPhoneNumber;
	private String passWord;
	public RequestLogin(String userPhoneNumber,String passWord)
	{
		this.userPhoneNumber=userPhoneNumber;
		this.passWord=passWord;
	}
	public String getUserPhoneNumber()
	{
		return this.userPhoneNumber;
	}
	public String getPassWord()
	{
		return this.passWord;
	}
	@Override
	public int getDataType() 
	{
		return dataType;
	}

}
