package com.itelg.texin.domain.exception;

import org.junit.Assert;
import org.junit.Test;

public class ContentValidationExceptionTest
{
	@Test
	public void testConstructor()
	{
		ContentValidationException exception = new ContentValidationException("error");
		Assert.assertEquals("error", exception.getMessage());
	}
}