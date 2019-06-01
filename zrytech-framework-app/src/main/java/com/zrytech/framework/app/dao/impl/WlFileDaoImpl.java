package com.zrytech.framework.app.dao.impl;

import com.zrytech.framework.app.dao.WlFileDao;
import com.zrytech.framework.app.entity.WlFile;
import com.zrytech.framework.app.entity.WlFileRelation;
import com.zrytech.framework.app.repository.WlFileRelationRepository;
import com.zrytech.framework.app.repository.WlFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Example;
@Repository
public class WlFileDaoImpl implements WlFileDao {

    @Autowired
    private WlFileRepository wlFileRepository;

    @Autowired
    private WlFileRelationRepository wlFileRelationRepository;

    @Override
    public WlFile save(WlFile wlFile) {
        return wlFileRepository.save(wlFile);
    }

    @Override
    public WlFile findOne(Integer fileId) {
        return wlFileRepository.findOne(fileId);
    }

    @Override
    public void relationOriginalFileAndCompressFile(WlFile original, WlFile compress) {
        WlFileRelation relation=new WlFileRelation();
        relation.setFileRelationOriginalId(original.getId());//原始文件id

        WlFileRelation one = wlFileRelationRepository.findOne(Example.of(relation)); //查找原始文件以前是否有过关联
        if(one != null){
            relation.setId(one.getId());//设置主键
        }

        relation.setFileRelationCompressId(compress.getId());//压缩文件id
        wlFileRelationRepository.save(relation);
    }

    @Override
    public WlFileRelation findFileRelationByOriginalId(Integer originalFileId) {
        WlFileRelation relation=new WlFileRelation();
        relation.setFileRelationOriginalId(originalFileId);//设置原始文件对象id
        return wlFileRelationRepository.findOne(Example.of(relation));
    }
}
