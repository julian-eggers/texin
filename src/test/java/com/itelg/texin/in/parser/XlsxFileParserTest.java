package com.itelg.texin.in.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.itelg.texin.domain.exception.ParsingFailedException;

public class XlsxFileParserTest
{
    private int parsedLines = 0;

    @Test
    public void testApplies()
    {
        assertThat(new XlsxFileParser().applies("test.csv")).isFalse();
        assertThat(new XlsxFileParser().applies("test.txt")).isFalse();
        assertThat(new XlsxFileParser().applies("test.xlsx")).isTrue();
    }

    @Test
    public void testParse() throws ParsingFailedException, IOException
    {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.xlsx"))
        {
            FileParser parser = new XlsxFileParser();

            parser.setRowParsedListener(row -> parsedLines++);
            parser.parse(stream);
            assertThat(parsedLines).isOne();
        }
    }
}
