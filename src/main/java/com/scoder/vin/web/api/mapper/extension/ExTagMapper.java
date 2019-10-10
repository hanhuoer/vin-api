package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author H
 */
@Repository
public interface ExTagMapper {

    List<ExTag> findTagByNoteId(@Param("noteId") Long noteId);

}
