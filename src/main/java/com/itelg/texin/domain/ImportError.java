package com.itelg.texin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportError
{
	private Cell cell;
	private String error;

	public ImportError(Cell cell, String error)
	{
		setCell(cell);
		setError(error);
	}

	@Override
	public String toString()
	{
		return "ImportError [cell=" + cell + ", error=" + error + "]";
	}
}
