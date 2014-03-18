package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import com.itelg.texin.domain.ImportError;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.RowParsedListener;


public abstract class SimpleImportProcessor<T> extends AbstractImportProcessor
{
	private Set<T> items = new LinkedHashSet<>();
	private Set<ImportError> importErrors = new LinkedHashSet<>();

	@Override
	protected void parseFile(final InputStream stream) throws ParsingFailedException, NoParserAppliedException
	{
		items = new LinkedHashSet<>();
		importErrors = new LinkedHashSet<>();

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
			mapRow(row);
		}
	}

	public abstract void mapRow(Row row);

	public void addItem(final T item)
	{
		items.add(item);
	}

	public void addImportError(final ImportError importError)
	{
		importErrors.add(importError);
	}

	public Set<ImportError> getImportErrors()
	{
		return importErrors;
	}

	public Set<T> getItems()
	{
		return items;
	}
}