package com.scoder.vin.web.api.mapper.basic;

import com.scoder.vin.web.api.domain.basic.NotePublish;
import com.scoder.vin.web.api.domain.basic.NotePublishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotePublishMapper {
    long countByExample(NotePublishExample example);

    int deleteByExample(NotePublishExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NotePublish record);

    int insertSelective(NotePublish record);

    List<NotePublish> selectByExample(NotePublishExample example);

    NotePublish selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NotePublish record, @Param("example") NotePublishExample example);

    int updateByExample(@Param("record") NotePublish record, @Param("example") NotePublishExample example);

    int updateByPrimaryKeySelective(NotePublish record);

    int updateByPrimaryKey(NotePublish record);
}