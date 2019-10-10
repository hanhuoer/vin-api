package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.basic.NoteDraft;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;

/**
 * @author H
 **/
public interface NoteDraftService {

    ExNoteDraft query(ExNoteDraft exNoteDraft);

    int append(ExNoteDraft exNoteDraft);

    int modify(ExNoteDraft exNoteDraft);

    int remove(ExNoteDraft exNoteDraft);

    NoteDraft content(Long draftId);

    ExNoteDraft lastDraftContent(Long userId, Long notebookId, Long noteId);

    ExNoteDraft getDraftContentByVersion(Long userId, Long notebookId, Long noteId, Long draftVersion);

    ExNoteDraft getLastVersionByNoteId(Long noteId);

}
