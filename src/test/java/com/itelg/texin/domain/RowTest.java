package com.itelg.texin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RowTest
{
    @Test
    void testConstructor()
    {
        Row row = new Row(1);
        assertThat(row.getNumber()).isOne();
    }

    @Test
    void testToString()
    {
        assertThat(new Row(1).toString().startsWith("Row")).isTrue();
    }
}
