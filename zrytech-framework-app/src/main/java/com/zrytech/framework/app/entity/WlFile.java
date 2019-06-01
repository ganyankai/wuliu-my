package com.zrytech.framework.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="sys_file")
public class WlFile extends com.zrytech.framework.app.domain.File implements Serializable{

    private static final long serialVersionUID = -8051038734724159669L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="file_origin_name")
    private String fileOriginName;//原始文件名称

    @Column(name="file_suffixes")
    private String fileSuffixes;//文件后缀

    @Column(name="file_path")
    private String filePath;//ftp服务器文件目标路径  文件路径风格

    @Column(name="file_fully_qualified_path")
    private String fileFullyQualifiedPath;//ftp服务器文件目标全路径 = path + originName

    @Column(name="file_size")
    private Long fileSize;//文件大小 单位是“字节”

    @Column(name="file_rewrite_name")
    private String fileRewriteName;//重写后的名称

    @Column(name="file_rewrite_fully_qualified_path")
    private String fileRewriteFullyQualifiedPath;//重写后的文件全路径 = path + rewriteName

    @Column(name="create_time",updatable = false)
    private Date createTime;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间

    @Column(name = "file_Key")
    private String fileKey;//更新时间

}
