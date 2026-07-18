package com.itelg.texin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ImportErrorTest
{
    @Test
    void testConstructor()
    {
        Cell cell = new Cell(new Row(1), 1, "test", "value");
        ImportError importError = new ImportError(cell, "error");

        assertThat(cell.getRow().getNumber()).isOne();
        assertThat(importError.getError()).isEqualTo("error");
    }

    @Test
    void testToString()
    {
        assertThat(new ImportError(null, "error").toString().startsWith("ImportError")).isTrue();
    }
}
