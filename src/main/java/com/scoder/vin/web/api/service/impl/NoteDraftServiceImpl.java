package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.basic.NoteDraft;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.mapper.basic.NoteDraftMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteDraftMapper;
import com.scoder.vin.web.api.service.NoteDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author H
 **/
@Service
public class NoteDraftServiceImpl implements NoteDraftService {

    @Autowired
    private NoteDraftMapper noteDraftMapper;
    @Autowired
    private ExNoteDraftMapper exNoteDraftMapper;

    @Override
    public ExNoteDraft query(ExNoteDraft exNoteDraft) {
        return (ExNoteDraft) noteDraftMapper.selectByPrimaryKey(exNoteDraft.getId());
    }

    @Override
    public int append(ExNoteDraft request) {
        request.setCreateTime(new Date());
        return noteDraftMapper.insertSelective(request);
    }

    @Override
    public int modify(ExNoteDraft request) {
        request.setUpdateTime(new Date());
        return noteDraftMapper.updateByPrimaryKeySelective(request);
    }

    @Override
    public int remove(ExNoteDraft request) {
        return noteDraftMapper.deleteByPrimaryKey(request.getId());
    }

    @Override
    public NoteDraft content(Long draftId) {
        return noteDraftMapper.selectByPrimaryKey(draftId);
    }

    @Override
    public ExNoteDraft lastDraftContent(Long userId, Long notebookId, Long noteId) {
        return exNoteDraftMapper.getLastVersionDraftByUserIdAndNotebookIdAndNoteId(userId, notebookId, noteId);
    }

    @Override
    public ExNoteDraft getDraftContentByVersion(Long userId, Long notebookId, Long noteId, Long draftVersion) {
        return null;
    }

    @Override
    public ExNoteDraft getLastVersionByNoteId(Long noteId) {
        Assert.notNull(noteId, "note id can not be null.");
        return exNoteDraftMapper.getLastVersionByNoteId(noteId);
    }
}
