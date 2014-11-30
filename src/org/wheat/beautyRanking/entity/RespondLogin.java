package org.wheat.beautyRanking.entity;

import org.wheat.beautyRanking.data.DataType;
import org.wheat.beautyRanking.data.IData;

public class RespondLogin implements IData
{
	private static final long serialVersionUID = 1L;
	private int respondCode=0;
	private int dataType=DataType.RESPOND_LOGIN;
	public RespondLogin(int respondCode)
	{
		this.respondCode=respondCode;
	}
	public int getRespondCode()
	{
		return this.respondCode;
	}
	@Override
	public int getDataType() 
	{
		return dataType;
	}

}
