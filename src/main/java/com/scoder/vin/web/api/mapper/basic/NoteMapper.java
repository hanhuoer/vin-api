package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.Note;
import com.scoder.vin.web.api.domain.basic.NoteExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteMapper {
    long countByExample(NoteExample example);

    int deleteByExample(NoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Note record);

    int insertSelective(Note record);

    List<Note> selectByExample(NoteExample example);

    Note selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Note record, @Param("example") NoteExample example);

    int updateByExample(@Param("record") Note record, @Param("example") NoteExample example);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);
}