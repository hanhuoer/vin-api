package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.module.AuthorAutoSave;
import com.scoder.vin.web.api.domain.module.AuthorPublish;
import com.scoder.vin.web.api.service.AuthorService;
import com.scoder.vin.web.api.service.NoteDraftService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_VIN_WEB + "/author/")
@Api(tags = {"Author"})
public class AuthorController {

    @Autowired
    private NoteDraftService noteDraftService;
    @Autowired
    private AuthorService authorService;

    @PostMapping("note/save")
    @ApiOperation(value = "auto save")
    public Response saveNote(@UserId Long userId, @RequestBody AuthorAutoSave authorAutoSave) {
        return Response.success(authorService.saveNote(userId, authorAutoSave));
    }

    @PostMapping("note/publish")
    @ApiOperation(value = "publish")
    public Response publishNote(@UserId Long userId, @RequestBody AuthorPublish authorPublish) {
        return Response.success(authorService.publishNote(authorPublish));
    }

    @PostMapping("tag/save")
    @ApiOperation(value = "tag save")
    public Response saveTag(@UserId Long userId, @RequestBody Object o) {
        return Response.success();
    }

}
