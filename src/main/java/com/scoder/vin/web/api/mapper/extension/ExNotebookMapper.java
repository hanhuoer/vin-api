package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExNotebook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author H
 **/
@Repository
public interface ExNotebookMapper {

    /**
     * 找出属于当前用户的所有 notebook
     *
     * @param userId user id
     * @return List<ExNotebook>
     */
    List<ExNotebook> findNotebookByUserId(@Param("userId") Long userId);

}
