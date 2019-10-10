package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.NoteTag;
import com.scoder.vin.web.api.domain.basic.NoteTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteTagMapper {
    long countByExample(NoteTagExample example);

    int deleteByExample(NoteTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NoteTag record);

    int insertSelective(NoteTag record);

    List<NoteTag> selectByExample(NoteTagExample example);

    NoteTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NoteTag record, @Param("example") NoteTagExample example);

    int updateByExample(@Param("record") NoteTag record, @Param("example") NoteTagExample example);

    int updateByPrimaryKeySelective(NoteTag record);

    int updateByPrimaryKey(NoteTag record);
}