package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class XlsxFileParserTest
{
	private Integer parsedLines = 0;

	@Test
	public void testApplies()
	{
		Assert.assertEquals(false, new XlsxFileParser().applies("test.csv"));
		Assert.assertEquals(false, new XlsxFileParser().applies("test.txt"));
		Assert.assertEquals(true, new XlsxFileParser().applies("test.xlsx"));
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
				System.out.println("Listener: " + row.getCells().iterator().next().getStringValue());
			}
		});
		parser.parse(stream);
		Assert.assertEquals(1, parsedLines, 0.1);
	}
}