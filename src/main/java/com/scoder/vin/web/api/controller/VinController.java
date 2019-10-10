package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.IgnoreAuth;
import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.common.page.HulkPage;
import com.scoder.vin.web.api.domain.extension.ExConfig;
import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.service.ConfigService;
import com.scoder.vin.web.api.service.NoteService;
import com.scoder.vin.web.api.service.UserService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_VISITOR_VIN)
@Api(tags = {"Vin"})
public class VinController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @PostMapping("index")
    @ApiOperation(value = "all notes", notes = "vin")
    @IgnoreAuth
    public Response<HulkPage> vinNoteList(@UserId Long userId, @RequestBody HulkPage<List<ExNote>> page) {
        return Response.success(noteService.noteList(page));
    }

    @PostMapping("archive")
    @ApiOperation(value = "vin archive")
    @IgnoreAuth
    public Response vinArchive(@UserId Long userId) {
        return Response.success(noteService.vinArchive());
    }

    @PostMapping("link")
    @ApiOperation(value = "vin link")
    @IgnoreAuth
    public Response vinLink() {
        ExConfig vinLink = configService.getByKey("VIN_LINK");
        if (vinLink == null) {
            return Response.success();
        }
        return Response.success(noteService.getVinVisitor(Long.parseLong(vinLink.getValue())));
    }

    @PostMapping("about")
    @ApiOperation(value = "vin about")
    @IgnoreAuth
    public Response vinAbout() {
        ExConfig vinAbout = configService.getByKey("VIN_ABOUT");
        if (vinAbout == null) {
            return Response.success();
        }
        return Response.success(noteService.getVinVisitor(Long.parseLong(vinAbout.getValue())));
    }

}
