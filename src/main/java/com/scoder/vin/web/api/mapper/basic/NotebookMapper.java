package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.Notebook;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Notebook record);

    int insertSelective(Notebook record);

    Notebook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notebook record);

    int updateByPrimaryKey(Notebook record);

}