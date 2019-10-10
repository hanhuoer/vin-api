package com.scoder.vin.web.api.domain.module;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author H
 */
@Data
@Builder
@ToString
public class Token {
    private String accessToken;
    private Integer expiredSeconds;
    private String name;
    private String avatar;
    private Long role;
}
