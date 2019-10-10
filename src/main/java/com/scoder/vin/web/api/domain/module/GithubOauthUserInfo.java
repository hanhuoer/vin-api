package com.scoder.vin.web.api.domain.module;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author H
 */
@Data
@ToString
public class GithubOauthUserInfo {

    private String login;
    private Long id;
    @JSONField(name = "node_id")
    private String nodeId;
    @JSONField(name = "avatar_url")
    private String avatarUrl;
    @JSONField(name = "gravatar_id")
    private String gravatarId;
    private String url;
    @JSONField(name = "html_url")
    private String htmlUrl;
    @JSONField(name = "followers_url")
    private String followersUrl;
    @JSONField(name = "following_url")
    private String followingUrl;
    @JSONField(name = "gists_url")
    private String gistsUrl;
    @JSONField(name = "starred_url")
    private String starredUrl;
    @JSONField(name = "subscriptions_url")
    private String subscriptionsUrl;
    @JSONField(name = "organizations_url")
    private String organizationsUrl;
    @JSONField(name = "repos_url")
    private String reposUrl;
    @JSONField(name = "events_url")
    private String eventsUrl;
    @JSONField(name = "received_events_url")
    private String receivedEventsUrl;
    private String type;
    @JSONField(name = "site_admin")
    private boolean siteAdmin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private Object hireable;
    private Object bio;
    @JSONField(name = "public_repos")
    private int publicRepos;
    @JSONField(name = "public_gists")
    private int publicGists;
    private int followers;
    private int following;
    @JSONField(name = "created_at")
    private String createdAt;
    @JSONField(name = "updated_at")
    private String updatedAt;

}
