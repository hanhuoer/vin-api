package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.extension.ExNoteDraft;

/**
 * @author shaokang
 **/
public interface NoteDraftService {

    ExNoteDraft query(ExNoteDraft exNoteDraft);
    
    int append(ExNoteDraft exNoteDraft);

    int modify(ExNoteDraft exNoteDraft);

    int remove(ExNoteDraft exNoteDraft);

    ExNoteDraft queryLastVersionByNoteId(Long noteId);
}
