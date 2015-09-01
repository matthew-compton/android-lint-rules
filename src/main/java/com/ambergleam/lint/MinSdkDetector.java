package com.ambergleam.lint;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Calendar;
import java.util.EnumSet;

public class MinSdkDetector extends Detector {

    private static final int SUGGESTED_MIN_SDK_VERSION = 15;
    private static final int YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private static final Implementation IMPLEMENTATION = new Implementation(
            MinSdkDetector.class,
            EnumSet.noneOf(Scope.class)
    );

    public static final Issue ISSUE = Issue.create(
            "MinSdk",
            "Supporting ancient Android versions",
            "It's " + YEAR + ", time to bump your minSdkVersion to " + SUGGESTED_MIN_SDK_VERSION,
            Category.CORRECTNESS,
            10,
            Severity.FATAL,
            IMPLEMENTATION
    );

    @Override
    public void afterCheckProject(Context context) {
        super.afterCheckProject(context);
        int minSdk = context.getProject().getMinSdk();
        if (minSdk != -1 && minSdk < SUGGESTED_MIN_SDK_VERSION) {
            context.report(ISSUE, null, "Ancient minSdkVersion detected");
        }
    }

}