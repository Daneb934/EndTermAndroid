package kz.astanamotorstest.ui_components.utils

import kz.astanamotorstest.ui_components.BuildConfig


object BuildType {

    fun isRelease() : Boolean {
        return BuildConfig.BUILD_TYPE == "release"
    }

    fun isDebug() : Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }

}