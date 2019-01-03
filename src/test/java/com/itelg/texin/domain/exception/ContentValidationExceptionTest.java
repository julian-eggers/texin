package com.itelg.texin.domain.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ContentValidationExceptionTest
{
    @Test
    public void testConstructor()
    {
        assertThat(new ContentValidationException("error").getMessage()).isEqualTo("error");
    }
}
