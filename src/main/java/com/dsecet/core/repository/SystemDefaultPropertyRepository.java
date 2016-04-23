package com.dsecet.core.repository;

import com.dsecet.core.domain.system.SystemDefaultProperty;

/**
 * Created with 66cf-v2
 * User : Ting.Yao
 * Date : 2015/3/2.
 */
public interface SystemDefaultPropertyRepository extends DomainRepository<SystemDefaultProperty>{

    SystemDefaultProperty findByActive(boolean active);
}
