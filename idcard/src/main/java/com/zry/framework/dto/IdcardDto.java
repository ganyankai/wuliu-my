package com.zry.framework.dto;/**
 * Created by zry on 2018/9/18.
 */

import com.zry.framework.annotation.Excelcol;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 10:46
 **/
@Data
public class IdcardDto {
    private Long id;
    @Excelcol("ID Number")
    private String idcard;
    @Excelcol("Name")
    private String name;
    private String birthday;
    private short checkStatus;
    private String error;
}
