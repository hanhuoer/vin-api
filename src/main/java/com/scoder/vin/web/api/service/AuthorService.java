package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.module.AuthorAutoSave;
import com.scoder.vin.web.api.domain.module.AuthorPublish;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorService {

    @Transactional
    Object saveNote(Long userId, AuthorAutoSave authorAutoSave);

    @Transactional
    Object publishNote(AuthorPublish authorPublish);

}
