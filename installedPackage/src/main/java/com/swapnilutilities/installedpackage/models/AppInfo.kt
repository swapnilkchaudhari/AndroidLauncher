package com.swapnilutilities.installedpackage.models

import android.graphics.drawable.Drawable

class AppInfo {
    var label: String? = null
    var packageName: String? = null
    var icon: Drawable? = null
    var version: String? = null
    var versionCode: String? = null

    override fun equals(other: Any?): Boolean {
        if (other is AppInfo) {
            return label?.equals(other.label) ?: false
        }
        return false
    }

}