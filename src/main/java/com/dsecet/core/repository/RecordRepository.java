package com.dsecet.core.repository;

import com.dsecet.foundation.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author: Fablenas
 */
@NoRepositoryBean
public interface RecordRepository<T extends Record> extends JpaRepository<T, Long>{
}
