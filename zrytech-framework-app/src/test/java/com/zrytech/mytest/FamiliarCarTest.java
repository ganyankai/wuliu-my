package com.zrytech.mytest;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.BaseApplication;
import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.mapper.FamiliarCarMapper;
import com.zrytech.framework.app.repository.FamiliarCarRepository;
import com.zrytech.framework.app.repository.OfenLocationRepository;
import com.zrytech.framework.base.entity.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class FamiliarCarTest {
    @Autowired
    private FamiliarCarRepository familiarCarRepository;

    @Autowired
    private FamiliarCarMapper familiarCarMapper;

    //ok
    @Test
    public void test1() {
        List<FamiliarCar> list = familiarCarRepository.findAll();
        System.out.println(list.size());
    }

    //ok
    @Test
    public void test2() {
        PageInfo<FamiliarCar> pageInfo = familiarCarMapper.familiarCarPage(new FamiliarCar(), new Page());
        System.out.println(pageInfo.getList().get(0).getCargoOwnnerId());
    }


}
