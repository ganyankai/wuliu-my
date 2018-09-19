package com.zry.framework.dto;/**
 * Created by zry on 2018/9/19.
 */

import com.zry.framework.annotation.Excelcol;
import lombok.Data;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-19 10:15
 **/
@Data
public class IdcardExportDto {
    @Excelcol("ID Number")
    private String idcard;
    @Excelcol("Name")
    private String name;
    @Excelcol("Birthday")
    private String birthday;
    @Excelcol("Result")
    private String checkStatus;
    @Excelcol("Error")
    private String error;
}
