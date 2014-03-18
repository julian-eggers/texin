package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class CsvFileParserTest
{
	private Integer parsedLines = 0;

	@Test
	public void testApplies()
	{
		Assert.assertEquals(true, new CsvFileParser().applies("test.csv"));
		Assert.assertEquals(false, new CsvFileParser().applies("test.txt"));
		Assert.assertEquals(false, new CsvFileParser().applies("test.xlsx"));
	}

	@Test
	public void testParse() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		FileParser parser = new CsvFileParser();

		parser.setRowParsedListener(new RowParsedListener()
		{
			@Override
			public void parsed(final Row row)
			{
				parsedLines++;
				System.out.println("Listener: " + row.getCells().iterator().next().getStringValue());
			}
		});
		parser.parse(stream);
		Assert.assertEquals(2, parsedLines, 0.1);
	}
}