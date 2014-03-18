package com.itelg.texin.in.processor;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class StreamingImportProcessorTest
{
	private int rows = 0;

	private class ImportProcessor extends StreamingImportProcessor
	{
		@Override
		public void process(Row row)
		{
			rows++;
		}
	}

	@Test
	public void testValidProcesser() throws ParsingFailedException, NoParserAppliedException
	{
		ImportProcessor processor = new ImportProcessor();
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv");

		rows = 0;
		processor.parse("testfile.csv", stream);
		Assert.assertEquals(2, rows);
	}
}