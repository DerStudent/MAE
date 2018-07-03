package de.fh.mae.md2.app.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ICategroryStatus.IN, ICategroryStatus.OUT})
@Retention(RetentionPolicy.SOURCE)
public @interface ICategroryStatus {
    int IN = 0;
    int OUT = 1;
}
