package com.itelg.texin.in.parser;

import java.io.InputStream;

import com.itelg.texin.domain.exception.ParsingFailedException;

public class OdsFileParser extends AbstractFileParser
{
	@Override
	public boolean applies(final String fileName)
	{
		return fileName.toLowerCase().endsWith(".ods");
	}

	@Override
	public void parse(final InputStream stream) throws ParsingFailedException
	{
	}
}