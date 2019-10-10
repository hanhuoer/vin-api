package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.common.page.HulkPage;
import com.scoder.vin.web.api.domain.extension.ExNote;

import java.util.List;

/**
 * @author H
 **/
public interface NoteService {
    int append(ExNote request);

    int modify(ExNote request);

    int delete(ExNote request);

    int remove(ExNote request);

    List<ExNote> notes(Long notebookId);

    List<ExNote> notes(Long userId, Long notebookId);

    /**
     * 只能查出来对外开放的 notebook 数据
     *
     * @param page
     * @return
     */
    HulkPage noteList(HulkPage<List<ExNote>> page);

    /**
     * @param noteId note id
     * @return ExNote
     */
    ExNote getVinVisitor(Long noteId);

    ExNote getPublishedNoteDetailOfVisitor(Long noteId);

    ExNote getPublishedNoteDetailOfMaster(Long noteId);

    /**
     * 文章归档
     *
     * @return
     */
    List vinArchive();

}
