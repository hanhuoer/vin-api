package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author H
 */
@Repository
public interface ExConfigMapper {

    ExConfig getByKey(@Param("key") String key);

}
