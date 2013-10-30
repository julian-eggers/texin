package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.itelg.texin.domain.ImportError;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.CsvFileParser;
import com.itelg.texin.in.parser.ExcelFileParser;
import com.itelg.texin.in.parser.FileParser;


public abstract class SimpleImportProcessor<T> implements ImportProcessor<T>
{
	private String fileName;
	protected Set<Row> rows = new LinkedHashSet<>();
	protected Set<T> items = new LinkedHashSet<>();
	protected Set<ImportError> importErrors = new LinkedHashSet<>();

	@Override
	public void parse(String fileName, InputStream stream) throws ParsingFailedException
	{
		this.fileName = fileName;
		parseFile(stream);
		IOUtils.closeQuietly(stream);

		for (Row row : getRows())
		{
			mapRow(row);
		}
	}

	protected void parseFile(InputStream stream) throws ParsingFailedException
	{
		List<FileParser> fileParsers = new ArrayList<>();
		fileParsers.add(new CsvFileParser());
		fileParsers.add(new ExcelFileParser());

		for (FileParser fileParser : fileParsers)
		{
			if (fileParser.applies(getFileName()))
			{
				rows = fileParser.parse(stream);
			}
		}
	}

	@Override
	public abstract void mapRow(Row row);

	@Override
	public void addItem(T item)
	{
		items.add(item);
	}

	@Override
	public String getFileName()
	{
		return fileName;
	}

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