package com.itelg.texin.in.parser;

import java.io.InputStream;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class CsvFileParserTest
{
	@Test
	public void testApplies()
	{
		Assert.assertEquals(true, new CsvFileParser().applies("test.csv"));
		Assert.assertEquals(true, new CsvFileParser().applies("test.txt"));
		Assert.assertEquals(false, new CsvFileParser().applies("test.xlsx"));
	}

	@Test
	public void testParse() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		Set<Row> rows = new CsvFileParser().parse(stream);
		Assert.assertEquals(2, rows.size());
	}

	@Test
	public void testParseWithListeners() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		FileParser csvFileParser = new CsvFileParser();
		csvFileParser.addRowParsedListener(new RowParsedListener()
		{
			@Override
			public void parsed(final Row row)
			{
			}
		});
		Set<Row> rows = csvFileParser.parse(stream);
		Assert.assertEquals(0, rows.size());
	}
}