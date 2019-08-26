package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.domain.Notebook;
import com.scoder.vin.web.api.service.NotebookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("query")
    public Object query(@RequestBody Notebook notebook) {
        return null;
    }

    @PostMapping("add")
    @ApiOperation("add")
    public Object add(@RequestBody Notebook notebook) {
        return null;
    }

    @PutMapping("modify")
    @ApiOperation("modify")
    public Object modify(@RequestBody Notebook notebook) {
        return null;
    }

    @DeleteMapping("remove")
    @ApiOperation("remove")
    public Object remove(@RequestBody Notebook notebook) {
        return null;
    }



}
