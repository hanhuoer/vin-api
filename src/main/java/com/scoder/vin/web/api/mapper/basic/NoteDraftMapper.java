package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.NoteDraft;
import com.scoder.vin.web.api.domain.basic.NoteDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteDraftMapper {
    long countByExample(NoteDraftExample example);

    int deleteByExample(NoteDraftExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NoteDraft record);

    int insertSelective(NoteDraft record);

    List<NoteDraft> selectByExample(NoteDraftExample example);

    NoteDraft selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NoteDraft record, @Param("example") NoteDraftExample example);

    int updateByExample(@Param("record") NoteDraft record, @Param("example") NoteDraftExample example);

    int updateByPrimaryKeySelective(NoteDraft record);

    int updateByPrimaryKey(NoteDraft record);
}