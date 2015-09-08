package com.itelg.texin.domain;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Row
{
	private int number;
	private Set<Cell> cells = new LinkedHashSet<>();

	public Row(final int number)
	{
		setNumber(number);
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(final int number)
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

	public Map<String, Object> getCellsAsMap()
	{
		Map<String, Object> cellMap = new LinkedHashMap<>();

		for (Cell cell : getCells())
		{
			cellMap.put(cell.getColumnHeader(), cell.getValue());
		}

		return cellMap;
	}

	@Override
	public String toString()
	{
		return "Row [number=" + number + ", cells=" + cells + "]";
	}
}