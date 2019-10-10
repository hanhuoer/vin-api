package com.scoder.vin.web.api.mapper.extension;

import com.scoder.vin.web.api.common.page.HulkPage;
import com.scoder.vin.web.api.domain.extension.ExNote;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author H
 **/
@Repository
public interface ExNoteMapper {

    List<ExNote> findByNotebookId(@Param("noteBookId") Long noteBookId);

    List<ExNote> findDraftByNotebookIdAndUserId(@Param("userId") Long userId, @Param("noteBookId") Long noteBookId);

    int updateByNoteIdSelective(ExNote exNote);

    List<ExNote> noteListData(HulkPage page);

    Integer noteListCount(HulkPage page);

    ExNote getVinVisitor(@Param("noteId") Long noteId);

    ExNote getPublishedNoteDetailByNoteIdOfVisitor(@Param("noteId") Long noteId);

    ExNote getPublishedNoteDetailByNoteIdOfMaster(@Param("noteId") Long noteId);

    ExNote getByNoteId(@Param("noteId") Long noteId);

    List<ExNote> findAllPublishedNote();

    int pageVisitorNumberPlusOne(@Param("noteId") Long noteId);
}
