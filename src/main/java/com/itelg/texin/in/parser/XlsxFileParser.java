package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.Row;
import com.itelg.texin.domain.exception.ParsingFailedException;

public class XlsxFileParser extends AbstractFileParser
{
	@Override
	public boolean applies(final String fileName)
	{
		return fileName.toLowerCase().endsWith(".xlsx");
	}

	@Override
	public void parse(final InputStream stream) throws ParsingFailedException
	{
		XSSFWorkbook workbook = null;
		
		try
		{
			workbook = new XSSFWorkbook(stream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (org.apache.poi.ss.usermodel.Row excelRow : sheet)
			{
				if (excelRow.getRowNum() == 0)
				{
					continue;
				}

				Row row = new Row(excelRow.getRowNum() + 1);

				for (int column = 0; column < excelRow.getLastCellNum(); column++)
				{
					org.apache.poi.ss.usermodel.Cell cell = excelRow.getCell(column, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
					String cellHeader = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().trim();

					// Detect cell-type
					Object cellValue;

					if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
					{
						if (DateUtil.isCellDateFormatted(cell))
						{
							cellValue = cell.getDateCellValue();
						}
						else
						{
							cellValue = Double.valueOf(cell.getNumericCellValue());

							if (((Double) cellValue).doubleValue() == Math.floor(((Double) cellValue).doubleValue()))
							{
								cellValue = Long.valueOf(((Double) cellValue).longValue());
							}
						}
					}
					else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN)
					{
						cellValue = Boolean.valueOf(cell.getBooleanCellValue());
					}
					else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING)
					{
						cellValue = cell.getStringCellValue().trim();
					}
					else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA)
					{
						switch (cell.getCachedFormulaResultType())
						{
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
							cellValue = Boolean.toString(cell.getBooleanCellValue());
							break;

						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
							cellValue = Double.valueOf(cell.getNumericCellValue());
							break;

						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
							cellValue = "";
							break;

						default:
						{
							if (cell.getHyperlink() != null)
							{
								cellValue = cell.getHyperlink().getAddress();
								break;
							}
							cellValue = cell.toString();
						}
						}

					}
					else
					{
						cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
						cellValue = cell.getStringCellValue().trim();
					}

					row.addCell(new Cell(row, (column + 1), cellHeader, cellValue));
				}

				rowParsedListener.parsed(row);
			}
		}
		catch (Exception e)
		{
			throw new ParsingFailedException(e.getMessage());
		}
		finally
		{
			IOUtils.closeQuietly(stream);
			IOUtils.closeQuietly(workbook);
		}
	}
}