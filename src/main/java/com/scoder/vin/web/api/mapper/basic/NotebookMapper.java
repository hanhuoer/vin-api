package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.Notebook;
import com.scoder.vin.web.api.domain.basic.NotebookExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookMapper {
    long countByExample(NotebookExample example);

    int deleteByExample(NotebookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notebook record);

    int insertSelective(Notebook record);

    List<Notebook> selectByExample(NotebookExample example);

    Notebook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Notebook record, @Param("example") NotebookExample example);

    int updateByExample(@Param("record") Notebook record, @Param("example") NotebookExample example);

    int updateByPrimaryKeySelective(Notebook record);

    int updateByPrimaryKey(Notebook record);
}