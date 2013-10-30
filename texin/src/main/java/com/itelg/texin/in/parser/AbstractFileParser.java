package com.itelg.texin.in.parser;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractFileParser implements FileParser
{
	protected List<RowParsedListener> listeners;

	@Override
	public void addRowParsedListener(final RowParsedListener listener)
	{
		if (listeners == null)
		{
			listeners = new ArrayList<>();
		}

		listeners.add(listener);
	}

	@Override
	public void addRowParsedListeners(final List<RowParsedListener> listeners)
	{
		for (RowParsedListener rowParsedListener : listeners)
		{
			addRowParsedListener(rowParsedListener);
		}
	}
}