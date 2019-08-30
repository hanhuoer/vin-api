package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author shaokang
 **/
@RestController
@RequestMapping("/note/")
@Api
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("append")
    @ApiOperation(value = "append note", notes = "append note")
    public Object append(@RequestBody ExNote request) {
        // condition
        Assert.notNull(request.getNotebookId(), "note's notebook id mustn't be null.");
        // insert one in to table.note
        noteService.append(request);
        return null;
    }

    @PutMapping("modify")
    @ApiOperation(value = "modify note", notes = "modify note")
    public Object modify(@RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        noteService.modify(request);
        return null;
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "remove note", notes = "remove note")
    public Object remove(@RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        noteService.remove(request);
        return null;
    }

    @PutMapping("move")
    @ApiOperation(value = "move note", notes = "move note")
    public Object move(@RequestBody ExNote request) {
        Assert.notNull(request.getId(), "note's id mustn't be null.");
        Assert.notNull(request.getNotebookId(), "note's notebook id mustn't be null.");
        return noteService.modify(request);
    }

}
