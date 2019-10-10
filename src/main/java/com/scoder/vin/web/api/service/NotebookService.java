package com.scoder.vin.web.api.service;

import com.scoder.vin.web.api.domain.extension.ExNote;
import com.scoder.vin.web.api.domain.extension.ExNotebook;

import java.util.List;

/**
 * @author H
 **/
public interface NotebookService {

    /**
     * 找出属于当前用户的所有 notebook
     *
     * @param userId user id
     * @return List<ExNotebook>
     */
    List<ExNotebook> query(Long userId);

    int append(ExNotebook notebook);

    int modify(ExNotebook notebook);

    int delete(ExNotebook notebook);

    int remove(ExNotebook notebook);

    List<ExNotebook> notebooks(Long userId);

    List<ExNote> note(Long userId, Long notebookId);

}
