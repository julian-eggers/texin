package com.itelg.texin.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.Row;

public class CsvFileParser implements FileParser
{
	private static final Logger log = LoggerFactory.getLogger(CsvFileParser.class);
	private static String delimeter = "\t";

	@Override
	public boolean applies(String fileName)
	{
		return (fileName.endsWith(".csv") || fileName.endsWith(".txt"));
	}

	@Override
	public Set<Row> parse(InputStream stream)
	{
		Set<Row> rows = new LinkedHashSet<Row>();
		InputStreamReader inputStreamReader = new InputStreamReader(stream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		try {

			String line;
			String header[] = null;
			int rowNumber = 0;

			while ((line = bufferedReader.readLine()) != null)
			{
				String[] cells = line.split(delimeter, -1);


				// Extract header-cells
				if (rowNumber == 0)
				{
					header = cells;
					rowNumber++;
					continue;
				}


				// Extract cells
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

				rows.add(row);
				rowNumber++;
			}

		} catch (IOException e) {

			log.error(e.getMessage(), e);

		} finally {

			IOUtils.closeQuietly(bufferedReader);
			IOUtils.closeQuietly(inputStreamReader);
		}

		return rows;
	}

	public static void setDelimeter(String delimeter)
	{
		CsvFileParser.delimeter = delimeter;
	}
}