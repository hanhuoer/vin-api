package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNotebook;
import com.scoder.vin.web.api.mapper.basic.NotebookMapper;
import com.scoder.vin.web.api.mapper.extension.ExNotebookMapper;
import com.scoder.vin.web.api.service.NotebookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author H
 **/
@Service
@Slf4j
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    private NotebookMapper notebookMapper;
    @Autowired
    private ExNotebookMapper exNotebookMapper;

    @Override
    public List<ExNotebook> query(Long userId) {
        return exNotebookMapper.findNotebookByUserId(userId);
    }

    @Override
    public int append(ExNotebook exNotebook) {
        exNotebook.setStatus(1);
        exNotebook.setCreateTime(new Date());
        return notebookMapper.insertSelective(exNotebook);
    }

    @Override
    public int modify(ExNotebook exNotebook) {
        exNotebook.setUpdateTime(new Date());
        return notebookMapper.updateByPrimaryKeySelective(exNotebook);
    }

    @Override
    public int delete(ExNotebook notebook) {
        return notebookMapper.deleteByPrimaryKey(notebook.getId());
    }

    @Override
    public int remove(ExNotebook exNotebook) {
        ExNotebook removeNotebook = new ExNotebook();
        removeNotebook.setId(exNotebook.getId());
        removeNotebook.setStatus(ExNotebook.statusDisable);
        removeNotebook.setUpdateTime(new Date());
        return notebookMapper.updateByPrimaryKeySelective(removeNotebook);
    }

    @Override
    public List<ExNotebook> notebooks(Long userId) {
        return exNotebookMapper.findNotebookByUserId(userId);
    }

    @Override
    public List<ExNote> note(Long userId, Long notebookId) {

        return null;
    }
}
