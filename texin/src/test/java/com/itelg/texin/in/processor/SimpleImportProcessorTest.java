package com.itelg.texin.in.processor;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class SimpleImportProcessorTest
{
	@Test
	public void testProcess() throws ParsingFailedException
	{
		ImportProcessor<TestObject> processor = new TestImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");
		processor.parse("testfile.csv", stream);
		Assert.assertEquals(2, processor.getItems().size());

		for (TestObject testObject : processor.getItems())
		{
			System.out.println(testObject.getColumn1());
		}
	}

	private class TestImportProcessor extends SimpleImportProcessor<TestObject>
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

		public void setColumn1(String column1)
		{
			this.column1 = column1;
		}
	}
}