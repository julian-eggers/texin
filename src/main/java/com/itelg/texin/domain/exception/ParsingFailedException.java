package com.itelg.texin.domain.exception;

public class ParsingFailedException extends Exception
{
	private static final long serialVersionUID = 6802209096277301767L;

	public ParsingFailedException(final String message)
	{
		super(message);
	}
}