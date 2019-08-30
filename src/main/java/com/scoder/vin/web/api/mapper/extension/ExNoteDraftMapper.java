package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author shaokang
 **/
@Repository
public interface ExNoteDraftMapper {
    ExNoteDraft queryLastVersionByNoteId(@Param("noteId") Long noteId);
}
