package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNoteDraft;
import com.scoder.vin.web.api.service.NoteDraftService;
import com.scoder.vin.web.api.service.NoteService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author H
 **/
@RestController
@RequestMapping(Router.API_VIN_WEB + "/note/draft/")
@Api(tags = {"NoteDraft"})
public class NoteDraftController {

    @Autowired
    private NoteDraftService noteDraftService;
    @Autowired
    private NoteService noteService;

    @PostMapping("save")
    @ApiOperation(value = "save", notes = "save to draft")
    public Response save(@UserId Long userId, @RequestBody ExNoteDraft request) {

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

        return Response.success();
    }

    @GetMapping("content/{draftId}")
    @ApiOperation(value = "content", notes = "")
    public Response content(@UserId Long userId, @PathVariable Long draftId) {
        return Response.success(noteDraftService.content(draftId));
    }

}
