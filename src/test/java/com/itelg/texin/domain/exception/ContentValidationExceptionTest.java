package com.itelg.texin.domain.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ContentValidationExceptionTest
{
    @Test
    void testConstructor()
    {
        assertThat(new ContentValidationException("error").getMessage()).isEqualTo("error");
    }
}
