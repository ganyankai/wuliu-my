package com.zrytech.framework.app.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件描述entity
 */
@Setter
@Getter
public abstract class File {
    private String fileOriginName;//原始文件名称
    private String fileSuffixes;//文件后缀
    private String filePath;//ftp服务器文件目标路径  文件路径风格
    private String fileFullyQualifiedPath;//ftp服务器文件目标全路径 = path + originName
    private Long fileSize;//文件大小
    private String fileRewriteName;//重写后的名称
    private String fileRewriteFullyQualifiedPath;//重写后的文件全路径 = path + rewriteName
    private String sourceUrl;
}
