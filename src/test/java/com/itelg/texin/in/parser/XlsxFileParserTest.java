package com.itelg.texin.in.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.itelg.texin.domain.exception.ParsingFailedException;

class XlsxFileParserTest
{
    private int parsedLines = 0;

    @Test
    void testApplies()
    {
        assertThat(new XlsxFileParser().applies("test.csv")).isFalse();
        assertThat(new XlsxFileParser().applies("test.txt")).isFalse();
        assertThat(new XlsxFileParser().applies("test.xlsx")).isTrue();
    }

    @Test
    void testParse() throws ParsingFailedException, IOException
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
