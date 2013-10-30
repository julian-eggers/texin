package com.itelg.texin.in.processor;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.CsvFileParser;
import com.itelg.texin.in.parser.RowParsedListener;

public class SimpleImportProcessorTest
{
	@Test
	public void testValidProcess() throws ParsingFailedException
	{
		ImportProcessor<TestObject> processor = new ValidTestImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		processor.parse("testfile.csv", stream);
		Assert.assertEquals(2, processor.getItems().size());

		for (TestObject testObject : processor.getItems())
		{
			System.out.println("TestObject: " + testObject.getColumn1());
		}
	}

	@Test
	public void testValidProcessWithListeners() throws ParsingFailedException
	{
		ImportProcessor<TestObject> processor = new ValidTestImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		processor.addRowParsedListener(new SimpleRowParsedListener());
		processor.parse("testfile.csv", stream);
		Assert.assertEquals(0, processor.getItems().size());
	}

	@Test
	public void testConfiguredFileParser() throws ParsingFailedException
	{
		ImportProcessor<TestObject> processor = new ValidTestImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile2.csv");

		CsvFileParser fileParser = new CsvFileParser();
		fileParser.setCellDelimeter("-");
		processor.addFileParser(fileParser);

		processor.parse("testfile2.csv", stream);
		Assert.assertEquals(1, processor.getRows().size());
		Assert.assertEquals(7, processor.getRows().iterator().next().getCells().size());
	}

	private class ValidTestImportProcessor extends SimpleImportProcessor<TestObject>
	{
		@Override
		public void mapRow(final Row row)
		{
			TestObject testObject = new TestObject();
			testObject.setColumn1(row.getCells().iterator().next().getStringValue());
			addItem(testObject);
		}
	}

	private class SimpleRowParsedListener implements RowParsedListener
	{
		@Override
		public void parsed(final Row row)
		{
			System.out.println("Listener: " + row.getCells().iterator().next().getStringValue());
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
}