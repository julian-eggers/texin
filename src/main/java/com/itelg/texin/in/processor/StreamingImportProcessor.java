package com.itelg.texin.in.processor;

import java.io.InputStream;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.FileParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class StreamingImportProcessor extends AbstractImportProcessor
{

    @Override
    protected void parseFile(final InputStream stream) throws ParsingFailedException, NoParserAppliedException
    {
        for (FileParser fileParser : fileParsers.values())
        {
            if (fileParser.applies(getFileName()))
            {
                fileParser.setRowParsedListener(row ->
                {
                    try
                    {
                        process(row);
                    }
                    catch (Exception e)
                    {
                        log.warn("Failed to parse row (" + row + ")", e);
                    }
                });
                fileParser.parse(stream);
                return;
            }
        }

        throw new NoParserAppliedException("No parser found for " + getFileName());
    }

    public abstract void process(Row row);
}
