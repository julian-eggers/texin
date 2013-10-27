package com.itelg.texin.parser;

import java.io.InputStream;
import java.util.Set;

import com.itelg.texin.domain.Row;

public interface FileParser
{
	public boolean applies(String fileName);

	public Set<Row> parse(InputStream stream);
}