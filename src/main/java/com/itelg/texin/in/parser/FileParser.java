package com.itelg.texin.in.parser;

import java.io.InputStream;

import com.itelg.texin.domain.exception.ParsingFailedException;

public interface FileParser
{
	public boolean applies(String fileName);

	public void parse(InputStream stream) throws ParsingFailedException;

	public void setRowParsedListener(RowParsedListener listener);
}