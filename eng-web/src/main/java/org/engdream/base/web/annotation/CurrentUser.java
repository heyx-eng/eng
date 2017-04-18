package org.engdream.base.web.annotation;


import org.engdream.security.Constants;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String value() default Constants.CURRENT_USER;

}
