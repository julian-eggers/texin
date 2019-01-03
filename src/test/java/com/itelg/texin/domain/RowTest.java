package com.itelg.texin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RowTest
{
    @Test
    public void testConstructor()
    {
        Row row = new Row(1);
        assertThat(row.getNumber()).isOne();
    }

    @Test
    public void testToString()
    {
        assertThat(new Row(1).toString().startsWith("Row")).isTrue();
    }
}
