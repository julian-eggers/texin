package com.itelg.texin.in.parser;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
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
        int rowNumber = 0;

        try (XSSFWorkbook workbook = new XSSFWorkbook(stream))
        {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (org.apache.poi.ss.usermodel.Row excelRow : sheet)
            {
                if (excelRow.getRowNum() == 0)
                {
                    continue;
                }

                rowNumber = excelRow.getRowNum() + 1;
                Row row = new Row(rowNumber);

                for (int column = 0; column < excelRow.getLastCellNum(); column++)
                {
                    org.apache.poi.ss.usermodel.Cell cell = excelRow.getCell(column, MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellHeader = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().trim();

                    Object cellValue = getCellValue(cell);

                    row.addCell(new Cell(row, (column + 1), cellHeader, cellValue));
                }

                rowParsedListener.parsed(row);
            }
        }
        catch (Exception e)
        {
            throw new ParsingFailedException(rowNumber, e);
        }
    }

    private static Object getCellValue(org.apache.poi.ss.usermodel.Cell cell)
    {
        if (cell.getCellType() == CellType.NUMERIC)
        {
            if (DateUtil.isCellDateFormatted(cell))
            {
                return cell.getDateCellValue();
            }

            Double cellValue = Double.valueOf(cell.getNumericCellValue());
            if (cellValue.doubleValue() == Math.floor(cellValue.doubleValue()))
            {
                return Long.valueOf(cellValue.longValue());
            }
            return cellValue;
        }
        else if (cell.getCellType() == CellType.BOOLEAN)
        {
            return Boolean.valueOf(cell.getBooleanCellValue());
        }
        else if (cell.getCellType() == CellType.STRING)
        {
            return cell.getStringCellValue().trim();
        }
        else if (cell.getCellType() == CellType.FORMULA)
        {
            switch (cell.getCachedFormulaResultType())
            {
                case BOOLEAN:
                    return Boolean.toString(cell.getBooleanCellValue());

                case NUMERIC:
                    return Double.valueOf(cell.getNumericCellValue());

                case BLANK:
                    return "";

                default:
                    if (cell.getHyperlink() != null)
                    {
                        return cell.getHyperlink().getAddress();
                    }
                    return cell.toString();
            }
        }
        else
        {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue().trim();
        }
    }
}
