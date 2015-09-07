package com.itelg.texin.domain.exception;

public class ParsingFailedException extends Exception
{
	private static final long serialVersionUID = 6802209096277301767L;
	private int rowNumber;

	public ParsingFailedException(int rowNumber, Throwable e)
	{
		super("Failed to parse row " + rowNumber + ": " + e.getMessage(), e);
		this.rowNumber = rowNumber;
	}
	
	public int getRowNumber()
	{
		return rowNumber;
	}
}