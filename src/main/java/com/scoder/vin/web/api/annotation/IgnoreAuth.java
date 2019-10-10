package com.scoder.vin.web.api.annotation;

import java.lang.annotation.*;

/**
 * @author H
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}