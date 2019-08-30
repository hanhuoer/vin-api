package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.extension.ExNote;

/**
 * @author shaokang
 **/
public interface NoteService {
    int append(ExNote request);

    int modify(ExNote request);

    int remove(ExNote request);
}
