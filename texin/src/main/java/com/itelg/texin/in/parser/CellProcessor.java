package com.itelg.texin.in.parser;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.exception.ContentValidationException;

public interface CellProcessor<T>
{
	public boolean applies(Cell cell);

	public void process(T item, Cell cell) throws ContentValidationException;
}