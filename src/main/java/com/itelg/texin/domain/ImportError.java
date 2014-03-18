package com.itelg.texin.domain;

public class ImportError
{
	private Cell cell;
	private String error;

	public ImportError(Cell cell, String error)
	{
		setCell(cell);
		setError(error);
	}

	public Cell getCell()
	{
		return cell;
	}

	public void setCell(Cell cell)
	{
		this.cell = cell;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}
}