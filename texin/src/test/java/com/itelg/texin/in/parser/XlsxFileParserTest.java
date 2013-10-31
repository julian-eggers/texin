package com.itelg.texin.in.parser;

import java.io.InputStream;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class XlsxFileParserTest
{
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
		Set<Row> rows = new XlsxFileParser().parse(stream);
		Assert.assertEquals(1, rows.size());
		Assert.assertEquals(5, rows.iterator().next().getCells().size());
	}

	@Test
	public void testParseWithListeners() throws ParsingFailedException
	{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.xlsx");
		FileParser fileParser = new XlsxFileParser();
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