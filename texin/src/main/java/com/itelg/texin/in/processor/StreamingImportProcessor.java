package com.itelg.texin.in.processor;

import java.io.InputStream;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.RowParsedListener;

public abstract class StreamingImportProcessor extends AbstractImportProcessor
{
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

		throw new NoParserAppliedException();
	}

	private class SimpleRowParsedListener implements RowParsedListener
	{
		@Override
		public void parsed(Row row)
		{
			process(row);
		}
	}

	public abstract void process(Row row);
}