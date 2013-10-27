package com.itelg.texin.parser;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itelg.texin.domain.Cell;
import com.itelg.texin.domain.Row;

public class ExcelFileParser implements FileParser
{
	private static final Logger log = LoggerFactory.getLogger(ExcelFileParser.class);

	@Override
	public boolean applies(String fileName)
	{
		return fileName.endsWith(".xlsx");
	}

	@Override
	public Set<Row> parse(InputStream stream)
	{
		Set<Row> rows = new LinkedHashSet<>();

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(stream);
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

						} else {

							cellValue = cell.getNumericCellValue();

							if ((double) cellValue == Math.floor((double) cellValue))
							{
								cellValue = (long) ((double) cellValue);
							}
						}

					} else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {

						cellValue = cell.getBooleanCellValue();

					} else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {

						cellValue = cell.getStringCellValue().trim();

					} else if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA) {

						switch (cell.getCachedFormulaResultType())
						{
							case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
							cellValue = Boolean.toString(cell.getBooleanCellValue());
							break;

							case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
							cellValue = cell.getNumericCellValue();
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

					} else {

						log.warn("Unknown cell-type(" + cell.getCellType() + " - " + cellHeader + ") - try to fetch string-value");
						cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
						cellValue = cell.getStringCellValue().trim();
					}

					row.addCell(new Cell(row, (column + 1), cellHeader, cellValue));
				}

				rows.add(row);
			}

		} catch (Exception e) {

			log.error(e.getMessage(), e);
		}

		return rows;
	}
}