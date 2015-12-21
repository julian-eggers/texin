package com.itelg.texin.domain;

import org.junit.Assert;
import org.junit.Test;

public class CellTest
{
	@Test
	public void testConstructor()
	{
		Cell cell = new Cell(new Row(1), 1, "test", "value");
		Assert.assertEquals(1, cell.getRow().getNumber(), 0);
		Assert.assertEquals(1, cell.getColumnNumber(), 0);
		Assert.assertEquals("test", cell.getColumnHeader());
		Assert.assertEquals("value", cell.getStringValue());
	}
	
	@Test
	public void testToString()
	{
		Assert.assertTrue(new Cell(null, 1, "key", "value").toString().startsWith("Cell"));
	}
}