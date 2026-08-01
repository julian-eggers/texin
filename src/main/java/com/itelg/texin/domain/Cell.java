package com.itelg.texin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell
{
	private Row row;
	private int columnNumber;
	private String columnHeader;
	private Object value;

	public Cell(Row row, int number, String header, Object value)
	{
		setRow(row);
		setColumnNumber(number);
		setColumnHeader(header);
		setValue(value);
	}

	public String getStringValue()
	{
		return getValue().toString();
	}

	public Integer getIntegerValue()
	{
		return Integer.valueOf(getStringValue());
	}

	public Long getLongValue()
	{
		return Long.valueOf(getStringValue());
	}

	public Double getDoubleValue()
	{
		return Double.valueOf(getStringValue());
	}

	@Override
	public String toString()
	{
		return "Cell [columnNumber=" + columnNumber + ", columnHeader=" + columnHeader + ", value=" + value + "]";
	}
}
