package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.basic.Notebook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shaokang
 **/
@Repository
public interface ExNotebookMapper {

    List<Notebook> findNotebookByUserId(@Param("userId") Long userId);

}
