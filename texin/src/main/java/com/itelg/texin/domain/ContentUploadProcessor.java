package com.itelg.texin.domain;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface ContentUploadProcessor<T>
{
	public void upload(String fileName, InputStream stream);

	public void processItems();

	public String getFileName();
	public Set<T> getItems();
	public List<ImportError> getImportErrors();
}