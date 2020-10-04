package com.swapnilutilities.installedpackage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.swapnilutilities.installedpackage.models.AppInfo
import java.lang.ref.WeakReference


class InstalledPackageManager(context: Context) : BroadcastReceiver() {

    private val contextReference: WeakReference<Context> = WeakReference(context)
    private var listener: PackageChangeListener? = null
    fun getInstalledApps(): ArrayList<AppInfo> {
        val appsList = arrayListOf<AppInfo>()
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        contextReference.get()?.packageManager.let { pManager ->

            val allApps = pManager.queryIntentActivities(intent, 0)
            allApps?.map { info ->
                val app = AppInfo()
                app.label = info.loadLabel(pManager).toString()
                app.packageName = info.activityInfo.packageName
                app.icon = info.activityInfo.loadIcon(pManager)
                val packageInfo = pManager.getPackageInfo(info.activityInfo.packageName, 0)
                app.version = packageInfo.versionName
                app.versionCode = packageInfo.versionCode.toString()
                appsList.add(app)
            }
        }
        return sort(appsList)
    }

    private fun sort(list: ArrayList<AppInfo>): ArrayList<AppInfo> {
        list.sortWith(Comparator { o1, o2 ->
            return@Comparator o1.label?.compareTo(o2.label ?: "") ?: 0
        })
        return list
    }

    fun registerPackageChangeListener(listener: PackageChangeListener) {
        Log.d("Darsh", "registerPackageChangeListener()")
        this.listener = listener

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addDataScheme("package")
        contextReference.get()?.let { it.registerReceiver(this, intentFilter) }
    }

    fun unRegisterPackageChangeListener() {
        Log.d("Darsh", "unRegisterPackageChangeListener()")
        contextReference.get()?.let { it.unregisterReceiver(this) }
        this.listener = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val packageName = intent?.data?.encodedSchemeSpecificPart
        listener?.packageInstalled(getInstalledApps())
        Log.d("Darsh", "onReceive() $packageName")
    }
}