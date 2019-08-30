package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.basic.Notebook;
import com.scoder.vin.web.api.mapper.basic.NotebookMapper;
import com.scoder.vin.web.api.mapper.extension.ExNotebookMapper;
import com.scoder.vin.web.api.service.NotebookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author shaokang
 **/
@Service
@Slf4j
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    private NotebookMapper notebookMapper;
    @Autowired
    private ExNotebookMapper exNotebookMapper;

    @Override
    public Object query(Long userId) {
        return exNotebookMapper.findNotebookByUserId(userId);
    }

    @Override
    public int append(Notebook notebook) {
        notebook.setStatus(1);
        notebook.setCreateTime(new Date());
        return notebookMapper.insertSelective(notebook);
    }

    @Override
    public int modify(Notebook notebook) {
        notebook.setUpdateTime(new Date());
        return notebookMapper.updateByPrimaryKeySelective(notebook);
    }

    @Override
    public int remove(Notebook notebook) {
        return notebookMapper.deleteByPrimaryKey(notebook.getId());
    }
}
