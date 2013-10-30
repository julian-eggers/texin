package com.itelg.texin.in.parser;

import java.io.InputStream;
import java.util.Set;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public interface FileParser
{
	public boolean applies(String fileName);

	public Set<Row> parse(InputStream stream) throws ParsingFailedException;

	/**
	 * If any listener is added, the row-set will be empty
	 *
	 */
	public void addRowParsedListener(RowParsedListener listener);
}