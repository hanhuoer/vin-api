package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.extension.ExConfig;

/**
 * @author H
 */
public interface ConfigService {

    ExConfig getByKey(String key);

}
