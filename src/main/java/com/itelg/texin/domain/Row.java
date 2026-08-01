package com.itelg.texin.domain;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Row
{
	private int number;
	private Set<Cell> cells = new LinkedHashSet<>();

	public Row(final int number)
	{
		setNumber(number);
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
