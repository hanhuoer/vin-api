package com.scoder.vin.web.api.domain.extension;

import com.scoder.vin.web.api.domain.basic.NotePublish;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author H
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExNotePublish extends NotePublish {

    public static ExNotePublish valueOf(ExNoteDraft exNoteDraft) {
        ExNotePublish exNotePublish = new ExNotePublish();
        exNotePublish.setId(exNoteDraft.getId());
        exNotePublish.setUserId(exNoteDraft.getUserId());
        exNotePublish.setNotebookId(exNoteDraft.getNotebookId());
        exNotePublish.setNoteId(exNoteDraft.getNoteId());
        exNotePublish.setTitle(exNoteDraft.getTitle());
        exNotePublish.setNoteAbstract(exNoteDraft.getNoteAbstract());
        exNotePublish.setContent(exNoteDraft.getContent());
        exNotePublish.setContentHtml(exNoteDraft.getContentHtml());
        exNotePublish.setVersion(null);
        exNotePublish.setOrigin(null);
        exNotePublish.setIp(null);
        return exNotePublish;
    }

}
