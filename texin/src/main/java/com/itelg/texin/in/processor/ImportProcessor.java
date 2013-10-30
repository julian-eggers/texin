package com.itelg.texin.in.processor;

import java.io.InputStream;
import java.util.Set;

import com.itelg.texin.domain.ImportError;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public interface ImportProcessor<T>
{
	public void parse(String fileName, InputStream stream) throws ParsingFailedException;
	public void mapRow(Row row);
	public void addItem(T item);

	public String getFileName();
	public Set<T> getItems();
	public Set<ImportError> getImportErrors();
}