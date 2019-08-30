package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.mapper.basic.NoteMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteMapper;
import com.scoder.vin.web.api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author shaokang
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private ExNoteMapper exNoteMapper;

    @Override
    public int append(ExNote request) {
        request.setCreateTime(new Date());
        return noteMapper.insertSelective(request);
    }

    @Override
    public int modify(ExNote request) {
        request.setUpdateTime(new Date());
        return noteMapper.updateByPrimaryKeySelective(request);
    }

    @Override
    public int remove(ExNote request) {
        return noteMapper.deleteByPrimaryKey(request.getId());
    }
}
