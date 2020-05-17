package com.czht.smartpark.common.core.annotation;


import com.czht.smartpark.common.core.log.LogAction;
import com.czht.smartpark.common.core.log.LogType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizLog {

    LogType logType() default LogType.OPERATION_LOG;

    LogAction logAction() default  LogAction.SELECT;


}
