package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.IgnoreAuth;
import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.common.page.HulkPage;
import com.scoder.vin.web.api.security.JwtHelper;
import com.scoder.vin.web.api.service.ConfigService;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_TEST_WEB + "vin/")
@Slf4j
@Api
public class TestController {

    @Autowired
    private ConfigService configService;

    @GetMapping("token")
    @ApiOperation(value = "token", notes = "默认返回 user = 1, 有效期 = 3600 秒钟的 token")
    public Response token() {
        return Response.success(JwtHelper.tokenGenerate(1L, 3600));
    }

    @GetMapping("token/{userId}/{expiredSeconds}")
    @ApiOperation(value = "token", notes = "token/{userId}/{expiredSeconds}")
    public Response token(@PathVariable Long userId, @PathVariable Integer expiredSeconds) {
        return Response.success(JwtHelper.tokenGenerate(userId, expiredSeconds));
    }

    @PostMapping("token/parse/{jwtToken}")
    @ApiOperation(value = "parse token", notes = "token/parse/{jwtToken}")
    public Response tests(@PathVariable String jwtToken) {
        return Response.success(JwtHelper.tokenParse(jwtToken));
    }

    @PostMapping("annotation/user")
    @ApiOperation(value = "annotation user", notes = "后端解析 token 后返回 userId")
    public Response annotationUser(@UserId Long userId) {
        return Response.success(userId);
    }

    @PostMapping("page/value")
    @ApiOperation(value = "page", notes = "page")
    @IgnoreAuth
    public Response pageTest(@RequestBody HulkPage page) {
        return Response.success(page);
    }

    @PostMapping("config/{key}")
    @ApiOperation(value = "get config by key")
    @IgnoreAuth
    public Response configTest(@PathVariable String key) {
        return Response.success(configService.getByKey(key));
    }

}
