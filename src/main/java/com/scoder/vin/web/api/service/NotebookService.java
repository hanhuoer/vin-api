package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.basic.Notebook;

/**
 * @author shaokang
 **/
public interface NotebookService {

    Object query(Long userId);

    int append(Notebook notebook);

    int modify(Notebook notebook);

    int remove(Notebook notebook);

}
