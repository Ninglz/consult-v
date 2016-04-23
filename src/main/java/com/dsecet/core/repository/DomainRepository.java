package com.dsecet.core.repository;

import com.dsecet.foundation.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author: Fablenas
 */
@NoRepositoryBean
public interface DomainRepository<T extends Domain> extends JpaRepository<T, Long>{
}
