package com.scoder.vin.web.api.domain.extension;

import com.scoder.vin.web.api.domain.basic.Notebook;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author H
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExNotebook extends Notebook {
    public static Integer statusEnable = 1;
    public static Integer statusDisable = 0;
}
