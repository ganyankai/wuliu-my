package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "货主常用地址entry")
@Entity
@Table(name = "`ofen_location`")
public class OfenLocation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "联系人名称", required = false)
    @Column(name = "`name`")
    private String name;

    @ApiModelProperty(value = "联系人手机号", required = false)
    @Column(name = "`tel`")
    private String tel;

    @ApiModelProperty(value = "省", required = false)
    @Column(name = "`province`")
    private String province;

    @ApiModelProperty(value = "市", required = false)
    @Column(name = "`city`")
    private String city;

    //--
    @ApiModelProperty(value = "县", required = false)
    @Column(name = "`county`")
    private String county;

    @ApiModelProperty(value = "详细地址", required = false)
    @Column(name = "`detailed_address`")
    private String detailedAddress;

    /** 经度 */
    @Column(name = "`longitude`")
    private BigDecimal longitude;

    /** 纬度 */
    @Column(name = "`latitude`")
    private BigDecimal latitude;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "`create_date`")
    private Date createDate;

    //--
    @ApiModelProperty(value = "货主Id", required = false)
    @Column(name = "`cargo_owner_id`")
    private Integer cargoOwnerId;
}
