package com.itelg.texin.in.parser;

import java.io.InputStream;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class TxtFileParserTest
{
	@Test
	public void testApplies()
	{
		Assert.assertEquals(false, new TxtFileParser().applies("test.csv"));
		Assert.assertEquals(true, new TxtFileParser().applies("test.txt"));
		Assert.assertEquals(false, new TxtFileParser().applies("test.xlsx"));
	}

	@Test
	public void testParse() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.txt");
		Set<Row> rows = new TxtFileParser().parse(stream);
		Assert.assertEquals(2, rows.size());
		Assert.assertEquals(6, rows.iterator().next().getCells().size());
	}

	@Test
	public void testParseWithListeners() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.txt");
		FileParser fileParser = new TxtFileParser();
		fileParser.addRowParsedListener(new RowParsedListener()
		{
			@Override
			public void parsed(final Row row)
			{
				System.out.println("Listener: " + row.getCells().iterator().next().getStringValue());
			}
		});
		Set<Row> rows = fileParser.parse(stream);
		Assert.assertEquals(0, rows.size());
	}
}