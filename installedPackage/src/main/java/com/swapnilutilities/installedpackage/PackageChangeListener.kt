package com.swapnilutilities.installedpackage

import com.swapnilutilities.installedpackage.models.AppInfo

interface PackageChangeListener {
    fun packageInstalled(list: ArrayList<AppInfo>)
}