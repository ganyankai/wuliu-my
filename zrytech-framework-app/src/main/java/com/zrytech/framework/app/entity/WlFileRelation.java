package com.zrytech.framework.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: xumeng
 * @Date: 2018/5/4 14:41
 * @Description:
 */
@Data
@Entity
@Table(name="sys_file_relation")
public class WlFileRelation implements Serializable {

    private static final long serialVersionUID = -2121650304940349757L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;//主键

    @Column(name="file_relation_original_id")
    private Integer fileRelationOriginalId; //原始图片id

    @Column(name="file_relation_compress_id")
    private Integer fileRelationCompressId;//压缩图片id

    @Column(name="file_relation_cut_id")
    private Integer fileRelationCutId;//剪裁图片id

    @Column(name="create_time",updatable = false)
    private Date createTime;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间

}
