package com.itelg.texin.in.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.itelg.texin.domain.exception.ParsingFailedException;

public class CsvFileParserTest
{
    private int parsedLines = 0;

    @Test
    public void testApplies()
    {
        assertThat(new CsvFileParser().applies("test.csv")).isTrue();
        assertThat(new CsvFileParser().applies("test.txt")).isFalse();
        assertThat(new CsvFileParser().applies("test.xlsx")).isFalse();
    }

    @Test
    public void testParse() throws ParsingFailedException, IOException
    {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv"))
        {
            FileParser parser = new CsvFileParser();

            parser.setRowParsedListener(row -> parsedLines++);
            parser.parse(stream);
            assertThat(parsedLines).isEqualTo(2);
        }
    }
}
