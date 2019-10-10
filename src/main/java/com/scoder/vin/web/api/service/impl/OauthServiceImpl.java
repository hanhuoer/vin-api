package com.scoder.vin.web.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.scoder.vin.web.api.domain.module.GithubOauthToken;
import com.scoder.vin.web.api.domain.module.GithubOauthUserInfo;
import com.scoder.vin.web.api.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author H
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Override
    public GithubOauthToken githubGetToken(String code, String clientId, String clientSecret) throws UnirestException {
        StringBuilder sb = new StringBuilder("https://github.com/login/oauth/access_token")
                .append("?")
                .append("client_id=")
                .append(clientId)
                .append("&")
                .append("client_secret=")
                .append(clientSecret)
                .append("&")
                .append("code=")
                .append(code);
        HttpResponse<String> tokenResponse = Unirest.post(sb.toString())
                .header("accept", "application/json")
                .asString();
        return JSONObject.parseObject(tokenResponse.getBody(), GithubOauthToken.class);
    }

    @Override
    public GithubOauthUserInfo githubGetUserInfo(String accessToken) throws UnirestException {
        HttpResponse<String> infoResponse = Unirest.get("https://api.github.com/user")
                .header("accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .asString();
        return JSONObject.parseObject(infoResponse.getBody(), GithubOauthUserInfo.class);
    }


}
