package com.itelg.texin.in.processor;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.CsvFileParser;

public class SimpleImportProcessorTest
{
	private class ImportProcessor extends SimpleImportProcessor<TestObject>
	{
		@Override
		public void mapRow(Row row)
		{
			TestObject testObject = new TestObject();
			testObject.setColumn1(row.getCells().iterator().next().getStringValue());
			addItem(testObject);
		}
	}

	private class TestObject
	{
		private String column1;

		public String getColumn1()
		{
			return column1;
		}

		public void setColumn1(final String column1)
		{
			this.column1 = column1;
		}
	}

	@Test
	public void testValidProcesser() throws ParsingFailedException, NoParserAppliedException
	{
		ImportProcessor processor = new ImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		processor.parse("testfile.csv", stream);
		Assert.assertEquals(2, processor.getItems().size());

		for (TestObject testObject : processor.getItems())
		{
			System.out.println("TestObject: " + testObject.getColumn1());
		}
	}

	@Test
	public void testConfiguredFileParser() throws ParsingFailedException, NoParserAppliedException
	{
		ImportProcessor processor = new ImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile2.csv");

		CsvFileParser fileParser = new CsvFileParser();
		fileParser.setCellDelimeter("-");
		processor.addFileParser(fileParser);

		processor.parse("testfile2.csv", stream);
		Assert.assertEquals(1, processor.getItems().size());
	}

	@Test(expected = NoParserAppliedException.class)
	public void testNoParsedAppliedException() throws ParsingFailedException, NoParserAppliedException
	{
		ImportProcessor processor = new ImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		processor.parse("testfile.pdf", stream);
	}
}