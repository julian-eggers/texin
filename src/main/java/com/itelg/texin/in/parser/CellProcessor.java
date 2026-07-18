package com.itelg.texin.in.parser;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.exception.ContentValidationException;

public interface CellProcessor<T>
{
	boolean applies(Cell cell);

	void process(T item, Cell cell) throws ContentValidationException;
}
