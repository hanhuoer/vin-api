package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.service.UserService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_VIN_WEB)
@Api(tags = {"Home"})
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("home/user/info")
    @ApiOperation(value = "user info", notes = "user info")
    public Response userInfo(@UserId Long userId) {
        return Response.success(userService.userInfo(userId));
    }

}
