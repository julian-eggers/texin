package com.itelg.texin.in.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class CsvFileParser extends AbstractFileParser
{
	private String cellDelimeter = ";";

	@Override
	public boolean applies(final String fileName)
	{
		return fileName.toLowerCase().endsWith(".csv");
	}

	@Override
	public void parse(final InputStream stream) throws ParsingFailedException
	{
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			String header[] = null;
			int rowNumber = 0;

			while ((line = reader.readLine()) != null)
			{
				String[] cells = line.split(cellDelimeter, -1);

				if (rowNumber == 0)
				{
					header = cells;
				}
				else
				{
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

					rowParsedListener.parsed(row);
				}

				rowNumber++;
			}
		}
		catch (IOException e)
		{
			throw new ParsingFailedException(e);
		}
		finally
		{
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(stream);
		}
	}

	public void setCellDelimeter(final String cellDelimeter)
	{
		this.cellDelimeter = cellDelimeter;
	}
}