package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.mapper.basic.NoteDraftMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteDraftMapper;
import com.scoder.vin.web.api.service.NoteDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author shaokang
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
    public ExNoteDraft queryLastVersionByNoteId(Long noteId) {
        return exNoteDraftMapper.queryLastVersionByNoteId(noteId);
    }
}
