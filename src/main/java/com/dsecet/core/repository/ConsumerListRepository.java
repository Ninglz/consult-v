package com.dsecet.core.repository;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.query.ConsumerVo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * User: lxl
 * Date: 14/12/11
 */
@NoRepositoryBean
public interface ConsumerListRepository extends DomainRepository<Consumer>{

    Page<Consumer> findConsumerList(ConsumerListRepositoryImpl.ConsumerQueryBuilder builder, Pageable pageable);

    List<Consumer> exportConsumers(Long from, Long to);

	Page<Consumer> queryByCondition(String keyword, Pageable pageable);

}
