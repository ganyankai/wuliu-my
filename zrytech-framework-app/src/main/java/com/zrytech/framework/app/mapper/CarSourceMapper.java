package com.zrytech.framework.app.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.entity.CarSource;

/**
 * 车源
 * 
 * @author cat
 *
 */
public interface CarSourceMapper {

	List<CarSource> selectSelective(CarSourcePageDto dto);

	/**
	 * 修改车源不需要审核的内容
	 * 
	 * @param id	车源Id
	 * @param startDate	发车时间
	 * @param isShare	是否拼车
	 * @param remark	备注
	 * @return
	 */
	int updateNoCheck(@Param("id") Integer id, @Param("startDate") Date startDate, @Param("isShare") Boolean isShare,
			@Param("remark") String remark);
	
	@Update("update `car_source` set status = #{status} where id = #{id}")
	int updateStatusById(@Param("id") Integer id, @Param("status") String status);
}