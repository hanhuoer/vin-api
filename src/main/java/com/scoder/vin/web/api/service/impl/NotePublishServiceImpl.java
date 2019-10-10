package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.mapper.basic.NotePublishMapper;
import com.scoder.vin.web.api.mapper.extension.ExNotePublishMapper;
import com.scoder.vin.web.api.service.NotePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author H
 **/
@Service
public class NotePublishServiceImpl implements NotePublishService {

    @Autowired
    private NotePublishMapper notePublishMapper;
    @Autowired
    private ExNotePublishMapper exNotePublishMapper;
}
