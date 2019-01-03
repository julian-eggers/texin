package com.itelg.texin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CellTest
{
    @Test
    public void testConstructor()
    {
        Cell cell = new Cell(new Row(1), 1, "test", "value");
        assertThat(cell.getRow().getNumber()).isOne();
        assertThat(cell.getColumnNumber()).isOne();
        assertThat(cell.getColumnHeader()).isEqualTo("test");
        assertThat(cell.getStringValue()).isEqualTo("value");
    }

    @Test
    public void testToString()
    {
        assertThat(new Cell(null, 1, "key", "value").toString().startsWith("Cell")).isTrue();
    }
}
