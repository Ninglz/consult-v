package com.dsecet.core.repository;

import com.dsecet.core.domain.user.Consumer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: lxl
 */
public interface ConsumerRepository extends DomainRepository<Consumer>{

    Consumer findByAuthenticationCellNoData(String userName);

    Consumer findByAuthentication_IdCard_Data(String idCard);

    @Query(value = "select count(id) from #{#entityName} c where c.authentication.cellNo.data =?1")
    Long countByCellNo(String cellNo);
    
    @Query(value = "from #{#entityName} p where p.id in (?1)")
    List<Consumer> findByIds(List<Long> ids);

    @Query(value = "select d.consumerId, f.id as equipmentId, f.mac, e.name as typeName from equipment f inner join (select distinct(c.mac) as mac, b.consumer_id as consumerId from equipment_consumer_record b inner join equipment c on b.equipment_id = c.id where b.consumer_id = ?1) d on f.mac = d.mac left join version_type e on f.version_type = e.id", nativeQuery = true)
    List queryConsumerEquipment(Long consumerId);

}
