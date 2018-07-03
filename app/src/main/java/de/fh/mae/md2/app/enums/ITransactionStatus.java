package de.fh.mae.md2.app.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ITransactionStatus.IN, ITransactionStatus.OUT})
@Retention(RetentionPolicy.SOURCE)
public @interface ITransactionStatus {
    int IN = 0;
    int OUT = 1;
}
