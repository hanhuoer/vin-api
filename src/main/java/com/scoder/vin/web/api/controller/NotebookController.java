package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.User;
import com.scoder.vin.web.api.domain.basic.Notebook;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.service.NotebookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shaokang
 **/
@RestController
@RequestMapping("/notebook/")
@Api(tags = {"notebook"})
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @GetMapping("query")
    @ApiOperation(value = "查询文集", notes = "查询文集")
    public Object query(@User Long userId) {
        return notebookService.query(userId);
    }

    @PostMapping("append")
    @ApiOperation(value = "添加文集", notes = "添加文集")
    public Object append(@RequestBody Notebook notebook) {
        Assert.hasLength(notebook.getName(), "notebook's name mustn't be null.");
        return notebookService.append(notebook);
    }

    @PutMapping("modify")
    @ApiOperation(value = "修改文集", notes = "修改文集")
    public Object modify(@RequestBody Notebook notebook) {
        Assert.notNull(notebook.getId(), "notebook's id mustn't be null.");
        return notebookService.modify(notebook);
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "删除文集", notes = "删除文集")
    public Object remove(@RequestBody Notebook notebook) {
        return notebookService.remove(notebook);
    }



}
