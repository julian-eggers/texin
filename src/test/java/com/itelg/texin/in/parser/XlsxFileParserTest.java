package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class XlsxFileParserTest
{
	private int parsedLines = 0;

	@Test
	public void testApplies()
	{
		Assert.assertFalse(new XlsxFileParser().applies("test.csv"));
		Assert.assertFalse(new XlsxFileParser().applies("test.txt"));
		Assert.assertTrue(new XlsxFileParser().applies("test.xlsx"));
	}

	@Test
	public void testParse() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.xlsx");
		FileParser parser = new XlsxFileParser();

		parser.setRowParsedListener(new RowParsedListener()
		{
			@Override
			public void parsed(final Row row)
			{
				parsedLines++;
			}
		});
		parser.parse(stream);
		Assert.assertEquals(1, parsedLines, 0.1);
	}
}