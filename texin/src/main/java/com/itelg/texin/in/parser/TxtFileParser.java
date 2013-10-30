package com.itelg.texin.in.parser;

public class TxtFileParser extends CsvFileParser
{
	public TxtFileParser()
	{
		setCellDelimeter("\t");
	}

	@Override
	public boolean applies(final String fileName)
	{
		return fileName.endsWith(".txt");
	}
}