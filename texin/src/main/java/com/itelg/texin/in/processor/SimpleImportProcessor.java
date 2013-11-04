package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.itelg.texin.domain.ImportError;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.CsvFileParser;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.RowParsedListener;
import com.itelg.texin.in.parser.TxtFileParser;
import com.itelg.texin.in.parser.XlsxFileParser;


public abstract class SimpleImportProcessor<T> implements ImportProcessor<T>
{
	private final Map<String, FileParser> fileParsers = new HashMap<>();
	private String fileName;
	private Set<Row> rows = new LinkedHashSet<>();
	private Set<T> items = new LinkedHashSet<>();
	private Set<ImportError> importErrors = new LinkedHashSet<>();
	private List<RowParsedListener> rowParsedListeners = new ArrayList<>();

	public SimpleImportProcessor()
	{
		addFileParser(new CsvFileParser());
		addFileParser(new TxtFileParser());
		addFileParser(new XlsxFileParser());
	}

	/**
	 * If any listener is added, the row-set will be empty
	 *
	 */
	@Override
	public void addRowParsedListener(final RowParsedListener listener)
	{
		if (rowParsedListeners.contains(listener) == false)
		{
			rowParsedListeners.add(listener);
		}
	}

	/**
	 * If any listener is added, the row-set will be empty
	 *
	 */
	@Override
	public void addRowParsedListeners(final List<RowParsedListener> listeners)
	{
		for (RowParsedListener listener : listeners)
		{
			addRowParsedListener(listener);
		}
	}

	@Override
	public void addFileParser(final FileParser fileParser)
	{
		if (fileParsers.containsKey(fileParser.getClass().getCanonicalName()))
		{
			fileParsers.remove(fileParser.getClass().getCanonicalName());
		}

		fileParsers.put(fileParser.getClass().getCanonicalName(), fileParser);
	}

	@Override
	public void parse(final String fileName, final InputStream stream) throws ParsingFailedException, NoParserAppliedException
	{
		rows = new LinkedHashSet<>();
		items = new LinkedHashSet<>();
		importErrors = new LinkedHashSet<>();
		this.fileName = fileName;
		parseFile(stream);
		IOUtils.closeQuietly(stream);

		for (Row row : getRows())
		{
			mapRow(row);
		}
	}

	protected void parseFile(final InputStream stream) throws ParsingFailedException, NoParserAppliedException
	{
		boolean parserApplied = false;

		for (FileParser fileParser : fileParsers.values())
		{
			if (fileParser.applies(getFileName()))
			{
				fileParser.addRowParsedListeners(rowParsedListeners);
				rows = fileParser.parse(stream);
				parserApplied = true;
				break;
			}
		}

		if (parserApplied == false)
		{
			throw new NoParserAppliedException();
		}
	}

	@Override
	public abstract void mapRow(Row row);

	@Override
	public void addItem(final T item)
	{
		items.add(item);
	}

	@Override
	public void addImportError(final ImportError importError)
	{
		importErrors.add(importError);
	}

	@Override
	public String getFileName()
	{
		return fileName;
	}

	@Override
	public Set<Row> getRows()
	{
		return rows;
	}

	@Override
	public Set<ImportError> getImportErrors()
	{
		return importErrors;
	}

	@Override
	public Set<T> getItems()
	{
		return items;
	}
}