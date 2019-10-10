package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.IgnoreAuth;
import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.extension.ExConfig;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.service.ConfigService;
import com.scoder.vin.web.api.service.NoteDraftService;
import com.scoder.vin.web.api.service.NoteService;
import com.scoder.vin.web.api.system.Router;
import com.scoder.vin.web.api.util.DateTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author H
 **/
@RestController
@RequestMapping(Router.API_VIN_WEB + "/note/")
@Api(tags = {"Note"})
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteDraftService noteDraftService;
    @Autowired
    private ConfigService configService;

    @PostMapping("append")
    @ApiOperation(value = "append note", notes = "append note")
    public Response append(@UserId Long userId, @RequestBody ExNote request) {
        Assert.notNull(request.getNotebookId(), "note's notebook id mustn't be null.");
        // 1. insert one in to table.note
        request.setUserId(userId);
        request.setNoteId(System.currentTimeMillis());
        noteService.append(request);

        // 2. new draft version
        ExNoteDraft exNoteDraft = new ExNoteDraft();
        exNoteDraft.setUserId(userId);
        exNoteDraft.setNotebookId(request.getNotebookId());
        exNoteDraft.setNoteId(request.getNoteId());
        exNoteDraft.setTitle(DateTimeUtils.formatDateTimeToString(new Date()));
        exNoteDraft.setContent("");
        exNoteDraft.setVersion(1);
        noteDraftService.append(exNoteDraft);

        return Response.success();
    }

    @PutMapping("modify")
    @ApiOperation(value = "modify note", notes = "modify note")
    public Response modify(@UserId Long userId, @RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        return Response.success(noteService.modify(request));
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "remove note", notes = "remove note")
    public Response remove(@UserId Long userId, @RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        return Response.success(noteService.remove(request));
    }

    @PutMapping("move")
    @ApiOperation(value = "move note", notes = "move note")
    public Response move(@UserId Long userId, @RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        Assert.notNull(request.getNotebookId(), "note's notebook id mustn't be null.");
        return Response.success(noteService.modify(request));
    }

    @GetMapping("notes")
    @ApiOperation(value = "all notes", notes = "query")
    public Response notes(@RequestParam Long notebookId) {
        return Response.success(noteService.notes(notebookId));
    }


    @PostMapping("detail/{noteId}")
    @IgnoreAuth
    public Response getPublishedNoteDetail(@UserId Long userId, @PathVariable("noteId") Long noteId) {
        if (Objects.isNull(userId)) {
            return Response.success(noteService.getPublishedNoteDetailOfVisitor(noteId));
        }

        ExConfig vinMaster = configService.getByKey("VIN_MASTER");
        try {
            if (userId.equals(Long.parseLong(vinMaster.getValue()))) {
                return Response.success(noteService.getPublishedNoteDetailOfMaster(noteId));
            }
        } catch (Exception ignored) {

        }

        return Response.success(noteService.getPublishedNoteDetailOfVisitor(noteId));
    }
}
