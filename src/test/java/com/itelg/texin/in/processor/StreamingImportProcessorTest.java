package com.itelg.texin.in.processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class StreamingImportProcessorTest
{
    private class ImportProcessor extends StreamingImportProcessor
    {
        protected int rows = 0;

        @Override
        public void process(Row row)
        {
            rows++;
        }
    }

    @Test
    public void testValidProcesser() throws ParsingFailedException, NoParserAppliedException, IOException
    {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("testfile.csv"))
        {
            ImportProcessor processor = new ImportProcessor();

            processor.parse("testfile.csv", stream);
            assertThat(processor.rows).isEqualTo(2);
        }
    }
}
