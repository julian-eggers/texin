package com.itelg.texin.in.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.itelg.texin.domain.exception.ParsingFailedException;

public class TxtFileParserTest
{
    private int parsedLines = 0;

    @Test
    public void testApplies()
    {
        assertThat(new TxtFileParser().applies("test.csv")).isFalse();
        assertThat(new TxtFileParser().applies("test.txt")).isTrue();
        assertThat(new TxtFileParser().applies("test.xlsx")).isFalse();
    }

    @Test
    public void testParse() throws ParsingFailedException, IOException
    {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.txt"))
        {
            FileParser parser = new TxtFileParser();

            parser.setRowParsedListener(row -> parsedLines++);
            parser.parse(stream);
            assertThat(parsedLines).isEqualTo(2);
        }
    }
}
