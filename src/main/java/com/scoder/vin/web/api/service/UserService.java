package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.basic.User;
import com.scoder.vin.web.api.domain.module.GithubOauthUserInfo;

public interface UserService {

    Boolean isExist(GithubOauthUserInfo githubOauthUserInfo);

    /**
     * check github oauth user info
     * if null then create new one
     * else return user info
     *
     * @param githubOauthUserInfo githubOauthUserInfo
     * @return ExUser
     */
    User checkByGithubOauth(GithubOauthUserInfo githubOauthUserInfo);

    User userInfo(Long userId);
}
