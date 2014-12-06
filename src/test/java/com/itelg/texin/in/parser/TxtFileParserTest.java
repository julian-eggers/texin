package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class TxtFileParserTest
{
	private int parsedLines = 0;

	@Test
	public void testApplies()
	{
		Assert.assertFalse(new TxtFileParser().applies("test.csv"));
		Assert.assertTrue(new TxtFileParser().applies("test.txt"));
		Assert.assertFalse(new TxtFileParser().applies("test.xlsx"));
	}

	@Test
	public void testParse() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.txt");
		FileParser parser = new TxtFileParser();

		parser.setRowParsedListener(new RowParsedListener()
		{
			@Override
			public void parsed(final Row row)
			{
				parsedLines++;
			}
		});
		parser.parse(stream);
		Assert.assertEquals(2, parsedLines, 0.1);
	}
}