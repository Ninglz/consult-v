package com.dsecet.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.record.Trading;

/**
 * 交易记录Repository
 * @author: heyue
 */
@NoRepositoryBean
public interface TradingRepository extends DomainRepository<Trading>{

	Page<Trading> findPage(Long id, String keyword, Long state, Pageable pageable,Long type,Long status);

}
