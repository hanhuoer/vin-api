package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author H
 */
@Repository
public interface ExUserMapper {

    ExUser selectByGithubId(@Param("githubId") Long id);
}
