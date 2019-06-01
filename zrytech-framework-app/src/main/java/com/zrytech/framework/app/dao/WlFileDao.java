package com.zrytech.framework.app.dao;

import com.zrytech.framework.app.entity.WlFile;
import com.zrytech.framework.app.entity.WlFileRelation;

public interface WlFileDao {
    /**
     * 保存文件对象并返回
     * @param wlFile
     * @return
     */
    WlFile save(WlFile wlFile);

    /**
     * 根据id获取文件对象
     * @param fileId
     * @return
     */
    WlFile findOne(Integer fileId);


    /**
     * 关联原始图片和压缩图片
     * @param original
     * @param compress
     */
    void relationOriginalFileAndCompressFile(WlFile original, WlFile compress);

    /**
     *  根据原始文件查询文件关联对象
      * @param originalFileId
     * @return
     */
    WlFileRelation findFileRelationByOriginalId(Integer originalFileId);
}
