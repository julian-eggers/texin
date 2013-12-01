package com.itelg.texin.in.parser;

public abstract class AbstractFileParser implements FileParser
{
	protected RowParsedListener rowParsedListener;

	@Override
	public void setRowParsedListener(RowParsedListener rowParsedListener)
	{
		this.rowParsedListener = rowParsedListener;
	}
}