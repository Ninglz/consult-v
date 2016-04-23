package com.dsecet.core.service.admin;

import java.text.ParseException;
import java.util.List;

import com.dsecet.core.domain.system.SystemDefaultProperty;

/**
 * @author: lxl
 */
public interface SystemDefaultPropertyService{

    SystemDefaultProperty save(Long id,Long freeTime,Long payTime,Long maxNum,
			 String cell,Double price,Double startPrice,Double proportion,Double consumerProportion,Double consumerPrice,Double consumerStartPrice,
			 Double freeIntegral,String timeForm,String timeTo,String consumerTimeForm,String consumerTimeTo) throws ParseException;

    SystemDefaultProperty delete(Long id);

    SystemDefaultProperty update(SystemDefaultProperty systemDefaultProperty);

    SystemDefaultProperty get(Long id);

    List<SystemDefaultProperty> findAll();

    SystemDefaultProperty getUsed();

}
