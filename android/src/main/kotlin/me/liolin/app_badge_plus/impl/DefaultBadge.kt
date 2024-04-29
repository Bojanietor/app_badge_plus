package me.liolin.app_badge_plus.impl

import android.content.Context
import android.content.Intent
import me.liolin.app_badge_plus.badge.IBadge
import me.liolin.app_badge_plus.util.LauncherTool

class DefaultBadge : IBadge {

    companion object {
        private const val INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE"
        private const val INTENT_EXTRA_PACKAGE_NAME = "badge_count_package_name"
        private const val INTENT_EXTRA_CLASS_NAME = "badge_count_class_name"
        private const val INTENT_EXTRA_BADGE_COUNT = "badge_count"
    }

    override fun updateBadge(context: Context, count: Int) {
        // Starting with Android 8.0 (API level 26), notification badges—also known as notification
        // dots—appear on a launcher icon when the associated app has an active notification. Users
        // can touch & hold the app icon to reveal the notifications, along with any app shortcuts.
        //
        // These dots appear by default in launcher apps that support them, and there's nothing
        // your app needs to do. However, there might be situations in which you don't want the to
        // notification dot to appear or you want to control exactly which notifications appear there.
        val launchClassName = LauncherTool.getClassName(context)
        val intent = Intent(INTENT_ACTION)
        intent.putExtra(INTENT_EXTRA_PACKAGE_NAME, context.packageName)
        intent.putExtra(INTENT_EXTRA_CLASS_NAME, launchClassName)
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, count)
        context.sendBroadcast(intent)
    }

    override fun getSupportLaunchers(): List<String> {
        return emptyList()
    }
}