/*
 * Copyright © 文科中的技术宅
 * blog:https://www.townwang.com
 */
package com.townwang.phone

/**
 * @author Town
 * @created at 2018/7/17 10:48
 * @Last Modified by: Town
 * @Last Email: android@townwang.com
 * @Last Modified time: 2018/7/17 10:48
 * @Remarks
 */

enum class Phone(var cmdPropName: String, var brand: String) {
    XIAOMI("ro.miui.ui.version.name", "xiaomi"), HUAWEI("ro.build.version.emui", "huawei"), OPPO("ro.build.version.opporom", "oppo"),
    VIVO("ro.vivo.os.build.display.id", "vivo"), GIONEE("ro.build.display.id", "gionee"), MEIZHU("ro.build.display.id", "meizu"),
    ZTE("ro.build.display.id", "zte"), COOLPAD("ro.build.display.id", "coolpad"), SAMSUNG("ro.build.display.id", "samsung"),
    HTC("ro.build.sense.version", "htc"), HONOR("ro.build.version.emui", "honor"),UNKNOWN("Unknown", "Unknown")
}

