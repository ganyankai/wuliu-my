package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dto.cargosource.CargoSourceSearchDto;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.Offer;
import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface WaybillDetailMapper {
      @Select("select wbd.id,c.car_no as carNo,cp.name as driverName,cp.id_card as driverIdCard,\n" +
              "\t\t     cp2.name as supercargoName,cp2.id_card as supercargoIdCard \n" +
              "\t\t     ,wb.id as waybillId,wb.name as waybillName,wbd.qty as qty, wbd.weight_unit as weightUnit"+
              "\t\t     from waybill wb,waybill_detail wbd,car c,car_person cp ,car_person cp2\n" +
              "\n\t " +
              "\t\t     where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id\n" +
              "\t\t\n" +
              "\t\t     and  wbd.driver_id = cp.id\n" +
              "\n" +
              "\t\t     and  wbd.supercargo_id = cp2.id\n" +
              "\n" +
              "\t\t     and  wb.car_ownner_id = #{carOwnnerId}")
      List<WaybillDetail> getCarWbdList(@Param("carOwnnerId") Integer carOwnnerId);


      @Select("select wbd.id,c.car_no as carNo,cp.name as driverName,cp.id_card as driverIdCard,\n" +
              "\t\t     cp2.name as supercargoName,cp2.id_card as supercargoIdCard \n" +
              "\t\t     ,wb.id as waybillId,wb.name as waybillName,wbd.qty as qty, wbd.weight_unit as weightUnit"+
              "\t\t     from waybill wb,waybill_detail wbd,car c,car_person cp ,car_person cp2  \n" +
              "\n" +
              "\t\t     where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id\n" +
              "\t\t\n" +
              "\t\t     and  wbd.driver_id = cp.id    \n" +
              "\n" +
              "\t\t     and  wbd.supercargo_id = cp2.id     \n" +
              "\n" +
              "\t\t     and  wb.cargo_ownner_id = #{cargoOwnnerId}")
      List<WaybillDetail> getCargoWbdList(@Param("cargoOwnnerId") Integer cargoOwnnerId);


}
