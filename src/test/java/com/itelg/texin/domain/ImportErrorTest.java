package com.itelg.texin.domain;

import org.junit.Assert;
import org.junit.Test;

public class ImportErrorTest
{
	@Test
	public void testConstructor()
	{
		Cell cell = new Cell(new Row(1), 1, "test", "value");
		ImportError importError = new ImportError(cell, "error");

		Assert.assertEquals(1, cell.getRow().getNumber(), 0.0);
		Assert.assertEquals("error", importError.getError());
	}
	
	@Test
	public void testToString()
	{
		Assert.assertTrue(new ImportError(null, "error").toString().startsWith("ImportError"));
	}
}