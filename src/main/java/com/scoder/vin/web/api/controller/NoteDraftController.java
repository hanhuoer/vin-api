package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.service.NoteDraftService;
import com.scoder.vin.web.api.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shaokang
 **/
@RestController
@RequestMapping("/note/draft/")
public class NoteDraftController {

    @Autowired
    private NoteDraftService noteDraftService;
    @Autowired
    private NoteService noteService;

    @PostMapping("save")
    @ApiOperation(value = "save", notes = "save to draft")
    public Object save(@RequestBody ExNoteDraft request) {

        // save draft
        int draftId = noteDraftService.append(request);

        // set draft version of note
        ExNote exNote = new ExNote();
        exNote.setId(request.getNoteId());
        exNote.setDraftVersion(
                noteDraftService.query(request)
                        .getVersion()
        );
        noteService.modify(exNote);

        return null;
    }

    @GetMapping("get/{noteId}/content")
    @ApiOperation(value = "get", notes = "这里的 note id 指文章随机数, 并非主键 id")
    public ExNoteDraft getContent(@PathVariable Long noteId) {
        return noteDraftService.queryLastVersionByNoteId(noteId);
    }

}
