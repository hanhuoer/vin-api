package com.scoder.vin.web.api.domain.extension;

import com.scoder.vin.web.api.domain.basic.Note;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author H
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExNote extends Note {

    public static Integer statusEnable = 1;
    public static Integer statusDisable = 0;

    private String notebookName;
    private List<ExTag> tags;

    private String title;
    private String noteAbstract;
    private String content;
    private String contentHtml;
    private String origin;
    private String ip;
    private Integer version;
    private Long draftId;
    private Long publishId;
    private Date publishTime;
    private Date draftTime;

    private String author;

}
