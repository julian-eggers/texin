package com.itelg.texin.in.parser;

import lombok.Setter;

public abstract class AbstractFileParser implements FileParser
{
	@Setter
	protected RowParsedListener rowParsedListener;
}
