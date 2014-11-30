package org.wheat.beautyRanking.entity;

import org.wheat.beautyRanking.data.DataType;
import org.wheat.beautyRanking.data.IData;

public class RequestCloseConnetion implements IData
{

	private static final long serialVersionUID = 1L;
	private int dataType=DataType.REQUEST_CLOSE_CONNETION;

	@Override
	public int getDataType() 
	{
		return dataType;
	}
	
}
