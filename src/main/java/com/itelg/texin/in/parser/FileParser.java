package com.itelg.texin.in.parser;

import java.io.InputStream;

import com.itelg.texin.domain.exception.ParsingFailedException;

public interface FileParser
{
	boolean applies(String fileName);

	void parse(InputStream stream) throws ParsingFailedException;

	void setRowParsedListener(RowParsedListener listener);
}
