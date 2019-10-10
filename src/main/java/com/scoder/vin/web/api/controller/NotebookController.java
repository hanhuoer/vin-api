package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNotebook;
import com.scoder.vin.web.api.service.NoteDraftService;
import com.scoder.vin.web.api.service.NoteService;
import com.scoder.vin.web.api.service.NotebookService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author H
 **/
@RestController
@RequestMapping(Router.API_VIN_WEB)
@Api(tags = {"Notebook"})
public class NotebookController {

    @Autowired
    private NotebookService notebookService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteDraftService noteDraftService;

    @PostMapping("notebook/append")
    @ApiOperation(value = "添加文集", notes = "添加文集")
    public Object append(@UserId Long userId, @RequestBody ExNotebook exNotebook) {
        Assert.hasLength(exNotebook.getName(), "exNotebook's name mustn't be null.");
        Assert.notNull(userId, "user id can not be null. please check request header's token.");
        exNotebook.setUserId(userId);
        return Response.success(notebookService.append(exNotebook));
    }

    @PutMapping("notebook/rename")
    @ApiOperation(value = "修改文集", notes = "修改文集")
    public Object rename(@UserId Long userId, @RequestBody ExNotebook exNotebook) {
        Assert.notNull(exNotebook.getId(), "exNotebook's id mustn't be null.");
        return Response.success(notebookService.modify(exNotebook));
    }

    @DeleteMapping("notebook/{notebookId}/remove")
    @ApiOperation(value = "删除文集", notes = "删除文集")
    public Object remove(@UserId Long userId, @PathVariable Long notebookId) {
        ExNotebook exNotebook = new ExNotebook();
        exNotebook.setId(notebookId);
        return Response.success(notebookService.remove(exNotebook));
    }

    @GetMapping("notebook/all")
    @ApiOperation(value = "所有笔记本", notes = "所有笔记本")
    public Response notebooks(@UserId Long userId) {
        return Response.success(notebookService.notebooks(userId));
    }

    @GetMapping("notebook/{notebookId}/note/all")
    @ApiOperation(value = "笔记本内所有笔记", notes = "笔记本内所有笔记")
    public Response note(@UserId Long userId,
                         @PathVariable Long notebookId) {
        return Response.success(noteService.notes(userId, notebookId));
    }

    @GetMapping("notebook/{notebookId}/note/{noteId}")
    @ApiOperation(value = "笔记", notes = "笔记")
    public Response note(@UserId Long userId,
                         @PathVariable Long notebookId,
                         @PathVariable Long noteId) {
        return Response.success();
    }

    @GetMapping("write/notebook/{notebookId}/note/{noteId}/content/{draftVersion}")
    @ApiOperation(value = "草稿内容", notes = "草稿内容")
    public Response draftContent(@UserId Long userId,
                                 @PathVariable Long notebookId,
                                 @PathVariable Long noteId,
                                 @PathVariable Long draftVersion) {
        return Response.success(noteDraftService.getDraftContentByVersion(userId, notebookId, noteId, draftVersion));
    }

    @GetMapping("write/notebook/{notebookId}/note/{noteId}/content")
    @ApiOperation(value = "草稿内容", notes = "草稿内容")
    public Response lastDraftContent(@UserId Long userId,
                                     @PathVariable Long notebookId,
                                     @PathVariable Long noteId) {
        return Response.success(noteDraftService.lastDraftContent(userId, notebookId, noteId));
    }

    @GetMapping("notebook/{notebookId}/note/{noteId}/content/{publishVersion}")
    @ApiOperation(value = "已发布内容", notes = "已发布内容")
    public Response publishContent(@UserId Long userId,
                                   @PathVariable Long notebookId,
                                   @PathVariable Long noteId,
                                   @PathVariable Long publishVersion) {
        return Response.success();
    }

    @DeleteMapping("notebook/{notebookId}/note/{noteId}/delete")
    @ApiOperation(value = "删除笔记", notes = "逻辑删除")
    public Response deleteNote(@UserId Long userId,
                               @PathVariable Long notebookId,
                               @PathVariable Long noteId) {
        ExNote removeNote = new ExNote();
        removeNote.setNoteId(noteId);
        return Response.success(noteService.remove(removeNote));
    }

}
