package com.itelg.texin.in.parser;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class OdsFileParser extends AbstractFileParser
{
	@Override
	public boolean applies(final String fileName)
	{
		return fileName.toLowerCase().endsWith(".ods");
	}

	@Override
	public Set<Row> parse(final InputStream stream) throws ParsingFailedException
	{
		Set<Row> rows = new LinkedHashSet<>();


		return rows;
	}
}