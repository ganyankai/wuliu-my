package com.zrytech.framework.app.mapper;

import com.zrytech.framework.app.entity.WaybillDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StatisticsMapper {
    //定位中心的数量(车主)
    @Select("select count(wbd.id) \n" +
            "\t\t   from waybill wb,waybill_detail wbd,car c,car_person cp ,car_person cp2\n" +
            "\n" +
            "\t\t   where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id\n" +
            "\t\t\n" +
            "\t\t   and  wbd.driver_id = cp.id\n" +
            "\n" +
            "\t\t   and  wbd.supercargo_id = cp2.id\n" +
            "\n" +
            "\t\t   and  wb.car_ownner_id = #{carOwnnerId}")
    Integer locCarCountCar(@Param("carOwnnerId") Integer carOwnnerId);

    //推荐货源的数量(车主)
    @Select("select count(c.id) from cargo c,cargo_send_record csr "+
            "          WHERE c.id = csr.cargo_id and csr.car_ownner_id = #{carOwnnerId} "+
            "          and c.status = 'cargo_source_status_release' "+
            "          and  c.`end_date` >= now() "+
            "          ORDER BY c.`create_date`")
    Integer recCargoCountCar(@Param("carOwnnerId") Integer carOwnnerId);

    //关注货主的数量(车主)
    @Select("select count(*) from my_focus_person where focuser_id = #{carOwnnerId} and focus_type = 'focus_cargo' \n")
    Integer followCargoOwnerCountCar(@Param("carOwnnerId") Integer carOwnnerId);


    //定位中心的数量(货主)
    @Select("select count(wbd.id) \n" +
            "\t\t   from waybill wb,waybill_detail wbd,car c,car_person cp ,car_person cp2\n" +
            "\n" +
            "\t\t   where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id\n" +
            "\t\t\n" +
            "\t\t   and  wbd.driver_id = cp.id  \n" +
            "\n" +
            "\t\t   and  wbd.supercargo_id = cp2.id \n" +
            "\n" +
            "\t\t   and  wb.cargo_ownner_id = #{cargoOwnnerId}")
    Integer locCarCountCargo(@Param("cargoOwnnerId") Integer cargoOwnnerId);

    //关注车主的数量(货主)
    @Select("select count(*) from my_focus_person where focuser_id = #{cargoOwnnerId} and focus_type = 'focus_car' \n")
    Integer followCarOwnerCountCar(@Param("cargoOwnnerId") Integer cargoOwnnerId);

    //熟车的数量(货主)
    @Select("select count(*) from familiar_car where cargo_ownner_id = #{cargoOwnnerId} ")
    Integer fimiliarCarCountCargo(@Param("cargoOwnnerId") Integer cargoOwnnerId);
}
