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
      @Select("select wbd.*,cp.* from waybill wb,waybill_detail wbd,car c,car_person cp  where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id "
              +
              " and c.driver_id = cp.id and  c.supercargo_id = cp.id  and  wb.car_ownner_id = #{carOwnnerId}")
      List<WaybillDetail> getCarWbdList(@Param("carOwnnerId") Integer carOwnnerId);


      @Select("select wbd.*,cp.* from waybill wb,waybill_detail wbd,car c,car_person cp  where wb.status='waybill_status_in_transit' and wbd.waybill_id = wb.id  and wbd.car_id = c.id "
            +
            " and c.driver_id = cp.id and  c.supercargo_id = cp.id and wb.cargo_ownner_id = #{cargoOwnnerId}")
      List<WaybillDetail> getCargoWbdList(@Param("cargoOwnnId") Integer cargoOwnnerId);


}
