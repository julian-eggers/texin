package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import com.itelg.texin.domain.ImportError;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.NoParserAppliedException;
import com.itelg.texin.domain.exception.ParsingFailedException;
import com.itelg.texin.in.parser.FileParser;
import com.itelg.texin.in.parser.RowParsedListener;

public interface ImportProcessor<T>
{
	/**
	 * If any listener is added, the row-set will be empty
	 *
	 */
	public void addRowParsedListener(RowParsedListener listener);

	/**
	 * If any listener is added, the row-set will be empty
	 *
	 */
	public void addRowParsedListeners(List<RowParsedListener> listeners);

	public void addFileParser(FileParser fileParser);
	public void parse(String fileName, InputStream stream) throws ParsingFailedException, NoParserAppliedException;
	public void mapRow(Row row);
	public void addItem(T item);
	public void addImportError(ImportError importError);

	public String getFileName();
	public Set<Row> getRows();
	public Set<T> getItems();
	public Set<ImportError> getImportErrors();
}