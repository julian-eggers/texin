package com.itelg.texin.in.processor;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.RowParsedListener;

public abstract class StreamingImportProcessor extends AbstractImportProcessor
{
	private static final Logger log = LoggerFactory.getLogger(StreamingImportProcessor.class);
	
	@Override
	protected void parseFile(final InputStream stream) throws ParsingFailedException, NoParserAppliedException
	{
		for (FileParser fileParser : fileParsers.values())
		{
			if (fileParser.applies(getFileName()))
			{
				fileParser.setRowParsedListener(new SimpleRowParsedListener());
				fileParser.parse(stream);
				return;
			}
		}

		throw new NoParserAppliedException("No parser found for " + getFileName());
	}

	private class SimpleRowParsedListener implements RowParsedListener
	{
		@Override
		public void parsed(Row row)
		{
			try
			{
				process(row);
			}
			catch (Exception e)
			{
				log.warn("Failed to parse row (" + row + ")", e);
			}
		}
	}

	public abstract void process(Row row);
}