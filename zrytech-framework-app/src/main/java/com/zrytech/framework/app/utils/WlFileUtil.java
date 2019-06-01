package com.zrytech.framework.app.utils;

import com.zrytech.framework.base.constant.Constant;
import com.zrytech.framework.app.domain.File;
import com.zrytech.framework.base.enums.BaseResult;
import com.zrytech.framework.base.enums.FileTypeEnum;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class WlFileUtil {

    /**
     * @param fullyQualifiedPath 给定上传到ftp服务上的文件的全路径 例如："|aaa|bbb|ccc.img"
     * @return
     */
    public static File getFile(String fullyQualifiedPath, Class< ? extends File> clazz){
        File file = null;
        try {
            file = clazz.newInstance();
        } catch (Exception e) {
            log.error("创建系统File对象失败:",e);
        }
        int suffixIndex = fullyQualifiedPath.lastIndexOf(".");
        if(suffixIndex == -1){
//            throw new BusinessException(new CommonResult(112,"文件后缀错误"));
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        file.setFileSuffixes(fullyQualifiedPath.substring(suffixIndex));// 设置suffixes 文件后缀 .img
        int lastFileSeparatorIndex = fullyQualifiedPath.lastIndexOf(Constant.FILE_SEPARATOR);
        if(lastFileSeparatorIndex != -1){
            file.setFileOriginName(fullyQualifiedPath.substring(lastFileSeparatorIndex+1)); //设置originName ccc.img
            file.setFilePath(fullyQualifiedPath.substring(0,lastFileSeparatorIndex));//设置path  |aaa|bbb
        }else {
            file.setFileOriginName(fullyQualifiedPath);//设置originName ccc.img
        }
        file.setFileFullyQualifiedPath(fullyQualifiedPath);//设置fullyQualifiedPath  |aaa|bbb|ccc.img
        String originName = file.getFileOriginName();
        String orginNameNoSuffixes = originName.substring(0, originName.lastIndexOf("."));
        file.setFileRewriteName(orginNameNoSuffixes+"_"+System.currentTimeMillis()+ file.getFileSuffixes());//设置从写后的名称
        if(StringUtils.isNotEmpty(file.getFilePath())){
            file.setFileRewriteFullyQualifiedPath(file.getFilePath()+ Constant.FILE_SEPARATOR+ file.getFileRewriteName());//设置RewriteFullyQualifiedPath
        }else {
            file.setFileRewriteFullyQualifiedPath(file.getFileRewriteName());//设置RewriteFullyQualifiedPath
        }
        return file;
    }

    /**
     * 根据文件全路径（或文件名）获取文件后缀  .img
     * @param fileName
     * @return
     */
    public static String getFileSuffixes(String fileName){
        int suffixIndex = fileName.lastIndexOf(".");
        if(suffixIndex == -1){
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return fileName.substring(suffixIndex);
    }

    /**
     * 根据文件全路径（或文件名）获取文件类型
     * @return
     */
    public static FileTypeEnum getFileType(String fileName){
        String fileSuffixes = getFileSuffixes(fileName);
        String fileSuffixesLowerCase = fileSuffixes.toLowerCase();
        if(Constant.picture.contains(fileSuffixesLowerCase)){
            return FileTypeEnum.picture;
        }
        if(Constant.video.contains(fileSuffixesLowerCase)){
            return FileTypeEnum.video;
        }
        if(Constant.text.contains(fileSuffixesLowerCase)){
            return FileTypeEnum.text;
        }
        return FileTypeEnum.other;
    }


    /**
     * 根据文件全路径（或文件名）获取文件类型
     * @return
     */
    public static String getfullyQualifiedPath(String fileName){
        FileTypeEnum fileType = getFileType(fileName);
        String filePath=null;
        switch (fileType){
            case picture:
                filePath= Constant.PIRCTURE_DIR;
                break;
            case video:
                filePath= Constant.VIDEO_DIR;
                break;
            case text:
                filePath= Constant.TEXT_DIR;
                break;
            case other:
                filePath= Constant.OTHER_DIR;
                break;
        }
        return filePath+fileName;
    }


    /**
     * 根据原始文件名称获取压缩后的文件名称例如 111.jpg--->111_thumb.jpg
     * @return
     */
    public static String getCompressFileName(String fileName){
        int suffixIndex = fileName.lastIndexOf(".");
        return fileName.substring(0,suffixIndex)+"_thumb"+fileName.substring(suffixIndex);
    }


}


