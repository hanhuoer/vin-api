package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.basic.User;
import com.scoder.vin.web.api.domain.extension.ExUser;
import com.scoder.vin.web.api.domain.module.GithubOauthUserInfo;
import com.scoder.vin.web.api.mapper.basic.UserMapper;
import com.scoder.vin.web.api.mapper.extension.ExUserMapper;
import com.scoder.vin.web.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ExUserMapper exUserMapper;

    @Override
    public Boolean isExist(GithubOauthUserInfo githubOauthUserInfo) {

        ExUser user = exUserMapper.selectByGithubId(githubOauthUserInfo.getId());
        if (null == user) {
            return false;
        }
        return true;
    }

    @Override
    public User checkByGithubOauth(GithubOauthUserInfo githubOauthUserInfo) {
        ExUser user = exUserMapper.selectByGithubId(githubOauthUserInfo.getId());
        if (null == user) {
            User newUser = new ExUser();
            newUser.setName(githubOauthUserInfo.getLogin());
            newUser.setAvatar(githubOauthUserInfo.getAvatarUrl());
            newUser.setGithubId(githubOauthUserInfo.getId());
            newUser.setCreateTime(new Date());
            userMapper.insertSelective(newUser);
            return newUser;
        }
        return user;
    }

    @Override
    public User userInfo(Long userId) {
        Assert.notNull(userId, "user id can not be null.");
        return userMapper.selectByPrimaryKey(userId);
    }
}
