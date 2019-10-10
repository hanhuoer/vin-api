package com.scoder.vin.web.api.domain.module;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author H
 */
@Data
@ToString
public class GithubOauthToken {

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "token_type")
    private String tokenType;
    @JSONField(name = "scope")
    private String scope;

}
