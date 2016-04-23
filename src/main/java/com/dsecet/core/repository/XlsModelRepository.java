package com.dsecet.core.repository;

import com.dsecet.core.domain.system.XlsModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: lxp
 * Date: 2015/6/27 15:28
 * safeguard-v1
 */
public interface XlsModelRepository extends JpaRepository<XlsModel, Long>{
    XlsModel findByCategory(XlsModel.ExportCategory category);
}
