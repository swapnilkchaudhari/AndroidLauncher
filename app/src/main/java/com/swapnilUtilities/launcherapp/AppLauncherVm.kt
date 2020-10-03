package com.swapnilUtilities.launcherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.swapnilutilities.installedpackage.InstalledPackageManager
import com.swapnilutilities.installedpackage.PackageChangeListener
import com.swapnilutilities.installedpackage.models.AppInfo

class AppLauncherVm(application: Application) : AndroidViewModel(application) {

    private val packageManager = InstalledPackageManager(getApplication())

    fun getInstalledApplicationList(): ArrayList<AppInfo> {
        return packageManager.getInstalledApps()
    }

    fun registerPackageChangeListener(listener: PackageChangeListener) {
        packageManager.registerPackageChangeListener(listener)
    }

    fun unRegisterPackageChangeListener() {
        packageManager.unRegisterPackageChangeListener()
    }
}