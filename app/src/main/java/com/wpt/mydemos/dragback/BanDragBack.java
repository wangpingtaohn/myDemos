package com.wpt.mydemos.dragback;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by HanHailong on 2017/6/20.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BanDragBack {

    boolean can() default true;

    String name() default "";
}
