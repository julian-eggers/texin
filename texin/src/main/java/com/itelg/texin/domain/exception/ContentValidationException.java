package com.itelg.texin.domain.exception;

public class ContentValidationException extends Exception
{
	private static final long serialVersionUID = -1776397724041723367L;

	public ContentValidationException(String message)
	{
		super(message);
	}
}