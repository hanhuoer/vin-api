package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.common.page.HulkPage;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExTag;
import com.scoder.vin.web.api.mapper.basic.NoteMapper;
import com.scoder.vin.web.api.mapper.extension.ExNoteMapper;
import com.scoder.vin.web.api.mapper.extension.ExTagMapper;
import com.scoder.vin.web.api.service.NoteService;
import com.scoder.vin.web.api.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author H
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private ExNoteMapper exNoteMapper;
    @Autowired
    private ExTagMapper exTagMapper;

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
    public int delete(ExNote request) {
        return noteMapper.deleteByPrimaryKey(request.getId());
    }

    @Override
    public int remove(ExNote request) {
        ExNote removeNote = new ExNote();
        removeNote.setNoteId(request.getNoteId());
        removeNote.setStatus(ExNote.statusDisable);
        removeNote.setUpdateTime(new Date());
        return exNoteMapper.updateByNoteIdSelective(removeNote);
    }

    @Override
    public List<ExNote> notes(Long notebookId) {
        return exNoteMapper.findByNotebookId(notebookId);
    }

    @Override
    public List<ExNote> notes(Long userId, Long notebookId) {
        return exNoteMapper.findDraftByNotebookIdAndUserId(userId, notebookId);
    }

    @Override
    public HulkPage noteList(HulkPage<List<ExNote>> page) {
        page.setData(exNoteMapper.noteListData(page));
        page.setTotalSize(exNoteMapper.noteListCount(page));
        page.setTotalPage(page.calculateTotalPage());
        page.setCurrentPage(page.getPageIndex());
        return page;
    }

    @Override
    public ExNote getVinVisitor(Long noteId) {
        ExNote exNote = exNoteMapper.getVinVisitor(noteId);
        if (Objects.isNull(exNote)) {
            return null;
        }
        List<ExTag> tags = exTagMapper.findTagByNoteId(noteId);
        exNote.setTags(tags);

        // update page visitor number
        int a = exNoteMapper.pageVisitorNumberPlusOne(noteId);
        return exNote;
    }

    @Override
    public ExNote getPublishedNoteDetailOfVisitor(Long noteId) {
        ExNote exNote = exNoteMapper.getPublishedNoteDetailByNoteIdOfVisitor(noteId);
        if (Objects.isNull(exNote)) {
            return null;
        }
        List<ExTag> tags = exTagMapper.findTagByNoteId(noteId);
        exNote.setTags(tags);

        // update page visitor number
        int a = exNoteMapper.pageVisitorNumberPlusOne(noteId);
        return exNote;
    }

    @Override
    public ExNote getPublishedNoteDetailOfMaster(Long noteId) {
        ExNote exNote = exNoteMapper.getPublishedNoteDetailByNoteIdOfMaster(noteId);
        if (Objects.isNull(exNote)) {
            return null;
        }
        List<ExTag> tags = exTagMapper.findTagByNoteId(noteId);
        exNote.setTags(tags);

        // update page visitor number
        int a = exNoteMapper.pageVisitorNumberPlusOne(noteId);
        return exNote;
    }

    @Override
    public List vinArchive() {
        List<ExNote> note = exNoteMapper.findAllPublishedNote();

        List<Map> data = new ArrayList<>();
        Set<String> dateList = new LinkedHashSet<>();
        note.forEach(n -> {
            dateList.add(DateTimeUtils.formatYearMonthToString(n.getFirstPublishTime()));
        });

        dateList.forEach(d -> {
            Set<ExNote> exNotes = new LinkedHashSet<>();
            note.forEach(n -> {
                if (DateTimeUtils.compareTwoDateEquals(d,
                        DateTimeUtils.formatYearMonthToString(n.getFirstPublishTime()))) {
                    exNotes.add(n);
                }
            });
            Map<String, Object> map = new HashMap<>(2);
            map.put("date", d);
            map.put("notes", exNotes);
            data.add(map);
        });
        return data;
    }


}
