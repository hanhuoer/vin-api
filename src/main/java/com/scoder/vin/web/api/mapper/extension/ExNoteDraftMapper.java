package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author H
 **/
@Repository
public interface ExNoteDraftMapper {
    ExNoteDraft getLastVersionByNoteId(@Param("noteId") Long noteId);

    ExNoteDraft getLastVersionDraftByUserIdAndNotebookIdAndNoteId(@Param("userId") Long userId,
                                                                  @Param("notebookId") Long notebookId,
                                                                  @Param("noteId") Long noteId);
}
