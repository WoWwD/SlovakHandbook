package com.app.slovakhandbook.utils

import android.app.Activity
import android.content.pm.ActivityInfo

fun screenOrientationToLandscape(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun screenOrientationToDefault(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}