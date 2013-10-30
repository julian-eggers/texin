package com.itelg.texin.in.parser;

import org.junit.Assert;
import org.junit.Test;

public class ExcelFileParserTest
{
	@Test
	public void testApplies()
	{
		Assert.assertEquals(false, new ExcelFileParser().applies("test.csv"));
		Assert.assertEquals(false, new ExcelFileParser().applies("test.txt"));
		Assert.assertEquals(true, new ExcelFileParser().applies("test.xlsx"));
	}
}