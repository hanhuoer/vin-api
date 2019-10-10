package com.scoder.vin.web.api.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.scoder.vin.web.api.annotation.IgnoreAuth;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.domain.basic.User;
import com.scoder.vin.web.api.domain.module.GithubOauthToken;
import com.scoder.vin.web.api.domain.module.GithubOauthUserInfo;
import com.scoder.vin.web.api.domain.module.Token;
import com.scoder.vin.web.api.security.JwtHelper;
import com.scoder.vin.web.api.service.ConfigService;
import com.scoder.vin.web.api.service.OauthService;
import com.scoder.vin.web.api.service.UserService;
import com.scoder.vin.web.api.system.Constant;
import com.scoder.vin.web.api.system.Router;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author H
 */
@RestController
@RequestMapping(Router.API_VIN_WEB + "/oauth/")
@Slf4j
@Api
public class OauthController {

    @Autowired
    private OauthService oauthService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;

    @PostMapping("get/github/client/id")
    @ApiOperation("sad")
    @IgnoreAuth
    public Response getGithubClientId() {
        return Response.success(configService.getByKey("OAUTH_GITHUB_CLIENT_ID"));
    }

    /**
     * 登录 -> 检查 user 表是否存在当前 github 用户的 id
     * 如果没有 -> 新建一个用户, 并插入 github id 给这个用户
     * 如果有 -> 找到这个 github id 对应的 user id
     *
     * @param params code
     * @return
     * @throws UnirestException
     */

    @PostMapping("github/redirect")
    @ApiOperation(value = "redirect", notes = "")
    @IgnoreAuth
    public Response githubRedirect(@RequestBody Map params) throws UnirestException {
        String code = (String) params.get("code");
        if (StringUtils.isEmpty(code)) {
            log.info("[oauth] code is null.");
            return Response.success(Constant.OAUTH_GITHUB_RECEIVE_CODE_FAILED.code(),
                    Constant.OAUTH_GITHUB_RECEIVE_CODE_FAILED.message(),
                    null);
        } else {
            log.info("[oauth] receive code is {}.", code);
            GithubOauthToken githubOauthToken = oauthService.githubGetToken(
                    code,
                    configService.getByKey("OAUTH_GITHUB_CLIENT_ID").getValue(),
                    configService.getByKey("OAUTH_GITHUB_CLIENT_SECRET").getValue()
            );
            if (Objects.isNull(githubOauthToken.getAccessToken())) {
                return Response.success(Constant.OAUTH_GITHUB_GET_ACCESS_TOKEN_FAILED.code(),
                        "please check the code: " + code,
                        null);
            } else {
                GithubOauthUserInfo githubOauthUserInfo = oauthService.githubGetUserInfo(githubOauthToken.getAccessToken());
                User user = userService.checkByGithubOauth(githubOauthUserInfo);

                Integer expiredSeconds = Integer.valueOf(configService.getByKey("SECURITY_JWT_TOKEN_EXPIRED_SECONDS").getValue());

                return Response.success(Token.builder()
                        .accessToken(JwtHelper.tokenGenerate(user.getId(), expiredSeconds))
                        .expiredSeconds(expiredSeconds)
                        .name(user.getName())
                        .avatar(user.getAvatar())
                        .role(user.getRoleId())
                        .build());
            }
        }
    }

}
