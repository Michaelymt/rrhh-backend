package pe.com.tss.runakuna.dto.importxls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import pe.com.tss.runakuna.enums.SiNoEnum;
import pe.com.tss.runakuna.util.EnumBase;
import pe.com.tss.runakuna.util.ExcelUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Created by josediaz on 8/11/2016.
 */
public class ExcelImporter {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    Row row;
    XlsMessageDto<?> xlsMessageDto;

    public ExcelImporter(Row row, XlsMessageDto<?> xlsMessageDto) {
        this.row = row;
        this.xlsMessageDto = xlsMessageDto;
    }

    public CellReader build(int colIdx) {
        return new CellReader(colIdx);
    }

    public class CellReader {
        int colIdx;
        String colTitle;
        Integer colMaxSize = null;
        Map<Object, List<Object>> aliasMap = null;
        List<Object> validValues = null;
        Function<Object, Object> valueProcessor = null;
        boolean isRequired = false;

        public CellReader(int colIdx) {
            this.colIdx = colIdx;
        }

        public CellReader alias(Object baseRef, Object... aliasRef) {
            if (aliasMap == null) aliasMap = new HashMap<>();
            aliasMap.put(baseRef, Arrays.asList(aliasRef));
            return this;
        }

        public CellReader aliasYesNo() {
            alias("X", "Y", "Yes");
            alias("", "N", "No");
            return this;
        }

        public CellReader validateUsingAlias() {
            validValues = new ArrayList<>(aliasMap.keySet());
            return this;
        }

        public SiNoEnum readEnumSiNo() {
            return readEnum(SiNoEnum.class);
        }

        public String readEnumSiNoCode() {
            return readEnum(SiNoEnum.class) == null ? null : readEnum(SiNoEnum.class).getCode();
        }

        public <TEnum extends EnumBase> TEnum readEnum(Class<TEnum> enumBase) {
            String value = readStr();
            if (value == null || value.length() == 0)
                return null;
            TEnum[] enumConstants = enumBase.getEnumConstants();
            for (int i = 0; i < enumConstants.length; i++) {
                TEnum enumConstant = enumConstants[i];
                if (enumConstant.getCode().equalsIgnoreCase(value))
                    return enumConstant;
            }
            return null;
        }

        public <TEnum extends EnumBase> TEnum readEnumValue(Class<TEnum> enumBase) {
            String value = readStr();
            if (value == null || value.length() == 0)
                return null;
            value = value.trim();
            TEnum[] enumConstants = enumBase.getEnumConstants();
            StringBuffer validStr = new StringBuffer();
            for (int i = 0; i < enumConstants.length; i++) {
                TEnum enumConstant = enumConstants[i];
                if (enumConstant.getLabel().equalsIgnoreCase(value) || enumConstant.getCode().equalsIgnoreCase(value))
                    return enumConstant;
                if (validStr.length() > 0) validStr.append(", ");
                validStr.append(enumConstant.getCode()).append("-").append(enumConstant.getLabel());
            }
            if (isRequired)
                xlsMessageDto.addError(buildErrorMessage(new RuntimeException("Value is incorrect. Valid: " + validStr)));
            return null;
        }


        public <TEnum extends EnumBase> TEnum readEnumValueCorrect(Class<TEnum> enumBase) {
            String value = readStr();
            if (value == null || value.length() == 0)
                return null;
            value = value.trim();
            TEnum[] enumConstants = enumBase.getEnumConstants();
            StringBuffer validStr = new StringBuffer();
            boolean valueCorrect = false;
            for (int i = 0; i < enumConstants.length; i++) {
                TEnum enumConstant = enumConstants[i];
                if (enumConstant.getLabel().equalsIgnoreCase(value) || enumConstant.getCode().equalsIgnoreCase(value)){
                    valueCorrect = true;
                    return enumConstant;
                }
                if (validStr.length() > 0) validStr.append(", ");
                validStr.append(enumConstant.getCode()).append("-").append(enumConstant.getLabel());
            }

            if(valueCorrect == false){
                xlsMessageDto.addError(buildErrorMessage(new RuntimeException("Value is incorrect. Valid: " + validStr)));
                return null;
            }

            if (isRequired)
                xlsMessageDto.addError(buildErrorMessage(new RuntimeException("Value is incorrect. Valid: " + validStr)));
            return null;
        }

        public <TEnum extends EnumBase> String readEnumCode(Class<TEnum> enumBase) {
            TEnum tEnum = readEnum(enumBase);
            return tEnum == null ? null : tEnum.getCode();
        }


        public String readZip() {
            String cadena = readStrZip();
            if(cadena!=null&&!Pattern.matches(".*[a-zA-Z]+.*", cadena)){
                valueProcessor = bigDecimal -> {
                    if (bigDecimal == null) return null;
                    long longValue = ((Number) bigDecimal).longValue();
                    String zipCode = String.valueOf(longValue);

                    BigDecimal b = new BigDecimal(zipCode);
                    return b;
                };
                BigDecimal bigDecimal = readNumber();
                return bigDecimal == null ? null : String.valueOf(bigDecimal.longValue());
            }else{
                return cadena;
            }
        }


        public String readStrZip() {
            return readCell(cell -> (ExcelUtils.getDataStringCell(cell,"#####")));
        }



        public String readStr() {
            return readCell(cell -> ExcelUtils.getDataStringCell(cell));
        }

        public String readStrAlways() {
            return readCell(cell -> ExcelUtils.getDataStringCellAlways(cell));
        }

        public String readStrLane() {
            return readCell(cell -> ExcelUtils.getDataStringCellLane(cell));
        }

        public BigDecimal readNumber() {
            return readCell(cell -> ExcelUtils.getDataBigDecimalCell(cell));
        }

        public BigDecimal readDecimal() {
            return readDecimal(null,null);
        }

        public BigDecimal readDecimal(Integer decimalDigits,BigDecimal maxValue) {
            BigDecimal bigDecimal = readCell(cell -> ExcelUtils.getDataBigDecimalAllowDecimalCell(cell));
            if (decimalDigits != null && bigDecimal != null)
                bigDecimal = bigDecimal.setScale(decimalDigits, BigDecimal.ROUND_HALF_UP);

            if (maxValue != null && bigDecimal != null) {
                if( bigDecimal.compareTo(maxValue) > 0){
                    xlsMessageDto.addError(buildErrorMessage(new RuntimeException("Value is exceeded. Valid max: " + maxValue)));
                }
            }

            return bigDecimal;
        }






        public Long readNumberLong() {
            return readCell(cell -> {
                BigDecimal val = ExcelUtils.getDataBigDecimalCell(cell);
                return val == null ? null : val.longValue();
            });
        }



        public String readNumber(String format) {
            BigDecimal number = readNumber();
            if (number == null) return null;
            return new DecimalFormat(format).format(number);
        }

        public Date readDate(String inputFormat) {
            return readCell(cell -> ExcelUtils.getDateCell(cell, inputFormat));
        }

        private <Type> Type readCell(Function<Cell, Type> cellReader) {
            try {
                Cell cell = row.getCell(colIdx);
                Type cellValue = cellReader.apply(cell);
                if (aliasMap != null)
                    for (Iterator iterator = aliasMap.keySet().iterator(); iterator.hasNext(); ) {
                        Object aliasBase = iterator.next();
                        List<Object> aliasRef = aliasMap.get(aliasBase);
                        if (aliasRef.contains(cellValue))
                            cellValue = (Type) aliasBase;
                    }
                if (valueProcessor != null)
                    cellValue = (Type) valueProcessor.apply(cellValue);
                if (colMaxSize != null && cellValue != null && cellValue.toString().length() > colMaxSize)
                    throw new IllegalArgumentException("Size sould not exceed " + colMaxSize);
                if (isRequired && (cellValue == null || (cellValue instanceof String && StringUtils.isEmpty(cellValue))))
                    throw new IllegalArgumentException("Value is required");
                return cellValue;
            } catch (Exception e) {
                log.error("error in read cell", e);
                xlsMessageDto.addError(buildErrorMessage(e));
                return null;
            }
        }

        public CellReader required() {
            this.isRequired = true;
            return this;
        }

        public CellReader title(String title) {
            colTitle = title;
            return this;
        }

        public CellReader maxSize(int maxSize) {
            colMaxSize = maxSize;
            return this;
        }

        private String buildErrorMessage(Throwable e) {
            String errorStr = e.getMessage();
            if (e instanceof NumberFormatException)
                errorStr = "Cannot convert to a valid number";

            if (row == null) return "Value is required";
            Cell cell = row.getCell(this.colIdx);
            char col = (char) ('A' + colIdx);
            String cellAddress = "" + col + (row.getRowNum() + 1);
            String msg = "[" + cellAddress;

            if (xlsMessageDto.getDtoReferenceId() != null && colTitle != null)
                msg += ", " + colTitle + " ID:" + xlsMessageDto.getDtoReferenceId();
            else if (xlsMessageDto.getDtoReferenceId() != null && colTitle == null)
                msg += ", ref :" + xlsMessageDto.getDtoReferenceId();
            else if (colTitle != null)
                msg += ", " + colTitle;


            String dataValueStr = (ExcelUtils.getData(cell) == null) ? "" : ExcelUtils.getData(cell);
            msg += ", value:" + (StringUtils.isEmpty(dataValueStr) ? "<empty>" : dataValueStr);
            msg += "]";
            return msg + " " + errorStr;
        }

    }
}
