package com.itelg.texin.domain;

import com.itelg.texin.domain.exception.ContentValidationException;


public interface ContentImporter<T>
{
	public boolean applies(Cell cell);

	public void importContent(T item, Cell cell) throws ContentValidationException;
}