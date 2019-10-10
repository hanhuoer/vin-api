package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.domain.extension.ExNotePublish;
import com.scoder.vin.web.api.domain.module.AuthorAutoSave;
import com.scoder.vin.web.api.domain.module.AuthorPublish;
import com.scoder.vin.web.api.mapper.basic.NoteDraftMapper;
import com.scoder.vin.web.api.mapper.basic.NoteMapper;
import com.scoder.vin.web.api.mapper.basic.NotePublishMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteDraftMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteMapper;
import com.scoder.vin.web.api.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * @author H
 */
@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private ExNoteDraftMapper exNoteDraftMapper;
    @Autowired
    private NoteDraftMapper noteDraftMapper;
    @Autowired
    private ExNoteMapper exNoteMapper;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private NotePublishMapper notePublishMapper;

    @Override
    @Transactional
    public Object saveNote(Long userId, AuthorAutoSave authorAutoSave) {
        // 1. get this content version
        ExNoteDraft latestVersionDraft = exNoteDraftMapper.getLastVersionByNoteId(authorAutoSave.getNoteId());
        int latestDraftVersion = 1;
        if (Objects.nonNull(latestVersionDraft.getVersion())) {
            latestDraftVersion = latestVersionDraft.getVersion();
        }

        // 2. insert new version into draft table
        ExNoteDraft exNoteDraft = new ExNoteDraft();
        exNoteDraft.setUserId(userId);
        exNoteDraft.setNotebookId(latestVersionDraft.getNotebookId());
        exNoteDraft.setNoteId(authorAutoSave.getNoteId());
        exNoteDraft.setTitle(authorAutoSave.getTitle());
        exNoteDraft.setContent(authorAutoSave.getContent());
        exNoteDraft.setContentHtml(authorAutoSave.getContentHtml());
        exNoteDraft.setVersion(latestDraftVersion + 1);
        exNoteDraft.setCreateTime(new Date());
        noteDraftMapper.insertSelective(exNoteDraft);

        // 3. update draft version for note, update latest edit time for note
        ExNote updateNote = new ExNote();
        updateNote.setNoteId(authorAutoSave.getNoteId());
        updateNote.setDraftVersion(latestDraftVersion + 1);
        updateNote.setLatestEditTime(new Date());
        updateNote.setUpdateTime(new Date());
        exNoteMapper.updateByNoteIdSelective(updateNote);

        return null;
    }

    @Override
    @Transactional
    public Object publishNote(AuthorPublish authorPublish) {

        // get meta
        ExNote exNote = exNoteMapper.getByNoteId(authorPublish.getNoteId());
        int latestPublishVersion = 1;
        if (Objects.nonNull(exNote.getPublishVersion())) {
            latestPublishVersion = exNote.getPublishVersion() + 1;
        }
        ExNoteDraft noteDraftLastVersion = exNoteDraftMapper.getLastVersionByNoteId(authorPublish.getNoteId());

        // insert latest version
        ExNotePublish exNotePublish = ExNotePublish.valueOf(noteDraftLastVersion);
        exNotePublish.setVersion(latestPublishVersion);
        exNotePublish.setCreateTime(new Date());
        int insert = notePublishMapper.insert(exNotePublish);

        // update publish version as latest, if first then set first publish time
        exNote.setPublishVersion(latestPublishVersion);
        exNote.setUpdateTime(new Date());
        if (latestPublishVersion == 1) {
            exNote.setFirstPublishTime(new Date());
        }
        int update = exNoteMapper.updateByNoteIdSelective(exNote);

        return null;
    }
}
