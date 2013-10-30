package com.itelg.texin.in.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class CsvFileParser extends AbstractFileParser
{
	private String cellDelimeter = ";";

	@Override
	public boolean applies(final String fileName)
	{
		return fileName.endsWith(".csv");
	}

	@Override
	public Set<Row> parse(final InputStream stream) throws ParsingFailedException
	{
		Set<Row> rows = new LinkedHashSet<Row>();

		try {

			CSVReader reader = new CSVReader(new InputStreamReader(stream), cellDelimeter.charAt(0));
			String[] cells;
			String header[] = null;
			int rowNumber = 0;

			while ((cells = reader.readNext()) != null)
			{
				if (rowNumber == 0)
				{
					header = cells;

				} else {

					Row row = new Row(rowNumber);

					if (cells.length > 0)
					{
						for (int cellNumber = 0; cellNumber < cells.length; cellNumber++)
						{
							String cellHeader = header[cellNumber];
							String cellValue = cells[cellNumber];
							row.addCell(new Cell(row, (cellNumber + 1), cellHeader, cellValue));
						}
					}

					if (listeners != null)
					{
						for (RowParsedListener listener : listeners)
						{
							listener.parsed(row);
						}

					} else {

						rows.add(row);
					}
				}

				rowNumber++;
			}

			reader.close();

		} catch (IOException e) {

			throw new ParsingFailedException(e.getMessage());
		}

		return rows;
	}

	public void setCellDelimeter(final String cellDelimeter)
	{
		this.cellDelimeter = cellDelimeter;
	}
}