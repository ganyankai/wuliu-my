package com.zry.framework.repository;/**
 * Created by zry on 2018/9/18.
 */

import com.zry.framework.domain.Idcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 10:45
 **/
@Repository
public interface IdcardRepository extends JpaRepository<Idcard,Long> {
}
