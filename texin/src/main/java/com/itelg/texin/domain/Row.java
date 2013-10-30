package com.itelg.texin.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Row
{
	private Integer number;
	private Set<Cell> cells = new LinkedHashSet<>();

	public Row(final Integer number)
	{
		setNumber(number);
	}

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(final Integer number)
	{
		this.number = number;
	}

	public Set<Cell> getCells()
	{
		return cells;
	}

	public void setCells(final Set<Cell> cells)
	{
		this.cells = cells;
	}

	public void addCell(final Cell cell)
	{
		getCells().add(cell);
	}
}