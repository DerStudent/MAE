package de.fh.mae.md2.app.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ICategroryType.INCOME, ICategroryType.OUTCOME})
@Retention(RetentionPolicy.SOURCE)
public @interface ICategroryType {
    int INCOME = 0;
    int OUTCOME = 1;
}
