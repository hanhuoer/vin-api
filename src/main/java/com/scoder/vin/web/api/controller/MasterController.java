package com.scoder.vin.web.api.controller;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.basic.User;
import com.scoder.vin.web.api.domain.extension.ExConfig;
import com.scoder.vin.web.api.service.ConfigService;
import com.scoder.vin.web.api.service.UserService;
import com.scoder.vin.web.api.system.Constant;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_MASTER_VIN)
@Api(tags = {"Master"})
public class MasterController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;

    @PostMapping("isMaster")
    @ApiOperation(value = "")
    public Response vinIsMaster(@UserId Long userId) {
        if (Objects.isNull(userId)) {
            return Response.success(Constant.VIN_USER_ID_CAN_NOT_BE_NULL.code(),
                    Constant.VIN_USER_ID_CAN_NOT_BE_NULL.message());
        }

        ExConfig vinMaster = configService.getByKey("VIN_MASTER");
        User user = userService.userInfo(userId);
        try {
            if (user.getId().equals(Long.parseLong(vinMaster.getValue()))) {
                return Response.success();
            }
        } catch (Exception e) {
            return Response.success(Constant.AUTH_DOES_NOTE_HAVE_PERMISSION.code(),
                    Constant.AUTH_DOES_NOTE_HAVE_PERMISSION.message());
        }
        return Response.success(Constant.AUTH_DOES_NOTE_HAVE_PERMISSION.code(),
                Constant.AUTH_DOES_NOTE_HAVE_PERMISSION.message());
    }

}
