package com.itelg.texin.domain;

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

	public Row getRow()
	{
		return row;
	}

	public void setRow(Row row)
	{
		this.row = row;
	}

	public int getColumnNumber()
	{
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber)
	{
		this.columnNumber = columnNumber;
	}

	public String getColumnHeader()
	{
		return columnHeader;
	}

	public void setColumnHeader(String columnHeader)
	{
		this.columnHeader = columnHeader;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public Object getValue()
	{
		return value;
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
}