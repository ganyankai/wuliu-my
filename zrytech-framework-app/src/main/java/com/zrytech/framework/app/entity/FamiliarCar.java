package com.zrytech.framework.app.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 车主货主相互关注表
 */
@Data
@Entity
@Table(name = "`familiar_car`")
public class FamiliarCar extends BaseEntity {
    private static final long serialVersionUID = 1763351958891301342L;

    /**主键，自增*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**关注者Id*/
    @Column(name = "`cargo_ownner_id`")
    private Integer cargoOwnnerId;

    /**被关注者Id*/
    @Column(name = "`car_ownner_id`")
    private Integer carOwnnerId;

    /**创建时间*/
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    private Date createDate;

}
