package com.zry.framework.domain;/**
 * Created by zry on 2018/9/18.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 10:46
 **/
@Deprecated
@Data
@Entity
@Table(name = "idcard_check")
public class Idcard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idcard")
    private String idcard;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "check_status")
    private short checkStatus;
    @Column(name = "error")
    private String error;
}
