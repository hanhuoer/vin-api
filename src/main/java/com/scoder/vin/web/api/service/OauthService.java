package com.scoder.vin.web.api.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.scoder.vin.web.api.domain.module.GithubOauthToken;
import com.scoder.vin.web.api.domain.module.GithubOauthUserInfo;

/**
 * @author H
 */
public interface OauthService {

    /**
     * get github token by code.
     *
     * @param code         code
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @return GithubOauthToken
     * @throws UnirestException unirestException
     */
    GithubOauthToken githubGetToken(String code, String clientId, String clientSecret) throws UnirestException;

    /**
     * get github user info by access token.
     *
     * @param accessToken access token
     * @return GithubOauthUserInfo
     */
    GithubOauthUserInfo githubGetUserInfo(String accessToken) throws UnirestException;
}
