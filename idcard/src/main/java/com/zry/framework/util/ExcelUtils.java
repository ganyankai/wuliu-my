package com.zry.framework.util;

import com.zry.framework.annotation.Excelcol;
import com.zrytech.framework.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
public class ExcelUtils {


    public static <T> List<T> resolution(File file, Class<T> clazz) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook( file );
        } catch (Exception e) {
            log.error("EXCEL解析出错", e );
            throw new BusinessException(105,"加载excel临时文件错误");
        }
        return resolution( xssfWorkbook, clazz );
    }

    ;

    public static <T> List<T> resolution(MultipartFile multipartFile, Class<T> clazz) {
        File f = null;
        try {
            f = File.createTempFile( "tmp", null );
            multipartFile.transferTo( f );
            f.deleteOnExit();
        } catch (IOException e) {
            log.error("EXCEL解析出错", e );
            throw new BusinessException(105,"加载excel临时文件错误");
        }
        return resolution( f, clazz );
    }

    ;

    public static <T> List<T> resolution(InputStream inputStream, Class<T> clazz) {
        File f = null;
        try {
            f = File.createTempFile( "tmp", null );
            OutputStream os = new FileOutputStream( f );
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read( buffer, 0, 8192 )) != -1) {
                os.write( buffer, 0, bytesRead );
            }
            os.close();
            inputStream.close();
        } catch (Exception e) {
            log.error("EXCEL解析出错", e );
            throw new BusinessException(104,"excel文件解析错误");
        }
        return resolution( f, clazz );
    }


    public static <T> List<T> resolution(XSSFWorkbook book, Class<T> clazz) {
        List<T> resultList = new ArrayList<>();
        if (book == null) {
            throw new BusinessException( 103,"获取excel文件错误" );
        }
        XSSFSheet sheet = book.getSheetAt( 0 );
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum < 1) {
            return new ArrayList<>();
        }
        for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
            XSSFRow row = sheet.getRow( rowNum );//获取每一行
            T t = null;
            try {
                t = row2Entity( sheet.getRow( 0 ), row, clazz );
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException( 99, "解析第" + (rowNum + 1) + "行失败：[" + e.getMessage() + "]" );
            }
            if (t != null) {
                resultList.add( t );
            }
        }
        return resultList;
    }

    private static <T> T row2Entity(XSSFRow head, XSSFRow row, Class<T> clazz) throws Exception {
        short lastCellNum = head.getLastCellNum();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < lastCellNum; i++) {
            map.put( head.getCell( i ).getStringCellValue(), row.getCell( i ) );
        }
        T t = clazz.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Excelcol) {
                        String ExcelField = ((Excelcol) annotation).value(); //获取到Excel的列名
                        field.setAccessible( true );//设置属性可访问权限
                        Class fieldType = field.getType();
                        Object value = getValue( map.get( ExcelField ) );
                        if (fieldType != null
                                && fieldType.equals( String.class )
                                ) {
                            String s = String.valueOf(value);
                            if(s != null  && !s.equals("null")){
                                field.set( t,s);
                            }
                        } else {
                            field.set( t, value );
                        }
                    }
                }
            }
        }
        return t;
    }

    private static Object getValue(Object o) {
        if (o == null)
            return null;
        XSSFCell cell = (XSSFCell) o;
        int cellType = cell.getCellType();//获取单元格值类型
        if (cellType == 1) {//代表String类型
            return cell.getStringCellValue();
        }
        if (cellType == 0) {
            if (HSSFDateUtil.isCellDateFormatted( cell )) {
                Instant instant = org.apache.poi.ss.usermodel.DateUtil.getJavaDate( cell.getNumericCellValue() ).toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                return instant.atZone( zoneId ).toLocalDateTime();

            }
            return new Long( ((long) cell.getNumericCellValue()) );
        }
        return null;
    }

    public static <T> Map<String, String> getSurfaceHead(Class<T> clazz) {
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Excelcol) {
                        String excelField = ((Excelcol) annotation).value(); //获取到Excel的列名
                        field.setAccessible( true );//设置属性可访问权限
                        String fieldName = field.getName();
                        map.put( fieldName, excelField );
                    }
                }
            }
        }
        return map;
    }

    public static <T, S> XSSFWorkbook export(List<T> list, Class<S> clazz) throws Exception {
        if (list == null || list.isEmpty()) {
            throw new BusinessException(102,"数据为空");
        }
        Map<String, String> map = getSurfaceHead( clazz );//获取excel与类的对应关系
        if (map.isEmpty()) {
            throw new BusinessException(101,"excel列头为空" );
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet = wb.createSheet( clazz.getName()+"导出" );
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        XSSFRow row1 = sheet.createRow( 0 );
        Integer i = 0;
        Map<String, Integer> index = new HashMap<>();
        for (String key : map.keySet()) {
            row1.createCell( i ).setCellValue( map.get( key ) );
            index.put( key, i );
            i++;
        }
        for (int z = 0; z < list.size(); z++) {
            XSSFRow row = sheet.createRow( z + 1 );
            Class c = list.get( z ).getClass();
            for (String key : map.keySet()) {
                Field field = c.getDeclaredField( key );
                field.setAccessible( true );
                setValue( row.createCell( index.get( key ) ), field.get( list.get( z ) ) );
            }
        }
        return wb;
    }

    public static void setValue(XSSFCell cell, Object o) {
        if (o instanceof Integer) {
            cell.setCellValue( (Integer) o );
        }
        if (o instanceof Long) {
            cell.setCellValue( (Long) o );
        }
        if (o instanceof Date) {
            cell.setCellValue( (Date) o );
        }
        if (o instanceof LocalDateTime) {
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = (LocalDateTime) o;
            ZonedDateTime zdt = localDateTime.atZone( zoneId );
            Date date = Date.from( zdt.toInstant() );
            cell.setCellValue( date );
        }
        if (o instanceof String) {
            cell.setCellValue( (String) o );
        }
    }

}
