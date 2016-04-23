package com.dsecet.core.repository.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.repository.DomainRepository;

/**
 * @author: heyue
 * 系统发送积分Repository
 */
@NoRepositoryBean
public interface SendIntegralRepository extends DomainRepository<SendIntegral>{

	Page<SendIntegral> findPage(String keyword, Pageable pageable);

}
