package com.scoder.vin.web.api.domain.basic;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author H
 */
@Data
@ToString
public class Config {

    private Long id;

    private String key;

    private String value;

    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}