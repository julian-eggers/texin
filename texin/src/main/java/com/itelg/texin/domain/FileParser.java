package com.itelg.texin.domain;

import java.io.InputStream;
import java.util.Set;

public interface FileParser
{
	public boolean applies(String fileName);

	public Set<Row> parse(InputStream stream);
}