package com.scoder.vin.web.api.service.impl;

import com.scoder.vin.web.api.domain.extension.ExConfig;
import com.scoder.vin.web.api.mapper.basic.ConfigMapper;
import com.scoder.vin.web.api.mapper.extension.ExConfigMapper;
import com.scoder.vin.web.api.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author H
 */
@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ExConfigMapper exConfigMapper;

    @Override
    public ExConfig getByKey(String key) {
        ExConfig config = exConfigMapper.getByKey(key);
        if (config == null || StringUtils.isEmpty(config.getValue())) {
            return null;
        }
        return config;
    }

}
