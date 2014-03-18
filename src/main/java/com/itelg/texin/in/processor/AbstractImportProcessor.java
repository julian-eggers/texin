package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.CsvFileParser;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.TxtFileParser;
import com.itelg.texin.in.parser.XlsxFileParser;

public abstract class AbstractImportProcessor
{
	protected Map<String, FileParser> fileParsers = new HashMap<>();
	private String fileName;

	public AbstractImportProcessor()
	{
		addFileParser(new CsvFileParser());
		addFileParser(new TxtFileParser());
		addFileParser(new XlsxFileParser());
	}

	public void addFileParser(final FileParser fileParser)
	{
		if (fileParsers.containsKey(fileParser.getClass().getCanonicalName()))
		{
			fileParsers.remove(fileParser.getClass().getCanonicalName());
		}

		fileParsers.put(fileParser.getClass().getCanonicalName(), fileParser);
	}

	public void parse(final String fileName, final InputStream stream) throws ParsingFailedException, NoParserAppliedException
	{
		this.fileName = fileName;
		parseFile(stream);
	}

	protected abstract void parseFile(InputStream stream) throws ParsingFailedException, NoParserAppliedException;

	public String getFileName()
	{
		return fileName;
	}
}