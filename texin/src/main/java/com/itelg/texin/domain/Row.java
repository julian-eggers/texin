package com.itelg.texin.domain;

import java.util.HashSet;
import java.util.Set;

public class Row
{
	private Integer number;
	private Set<Cell> cells = new HashSet<>();

	public Row(Integer number)
	{
		setNumber(number);
	}

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public Set<Cell> getCells()
	{
		return cells;
	}

	public void setCells(Set<Cell> cells)
	{
		this.cells = cells;
	}

	public void addCell(Cell cell)
	{
		getCells().add(cell);
	}
}