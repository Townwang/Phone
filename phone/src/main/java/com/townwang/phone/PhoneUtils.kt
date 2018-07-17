/*
 * Copyright © 文科中的技术宅
 * blog:https://www.townwang.com
 */
package com.townwang.phone
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.townwang.phone.Phone.*
import java.util.*
/**
 * @author Town
 * @created at 2018/7/17 10:35
 * @Last Modified by: Town
 * @Last Email: android@townwang.com
 * @Last Modified time: 2018/7/17 10:35
 * @Remarks phone utils
 */
object PhoneUtils {
    /**
     *  get phone model .
     *  need method parameter { Build.BRAND}
     */
    fun getPhone(): Phone {
        return when (android.os.Build.BRAND.toLowerCase()) {
            XIAOMI.brand -> XIAOMI

            HUAWEI.brand -> HUAWEI

            HONOR.brand -> HONOR

            MEIZHU.brand -> MEIZHU

            OPPO.brand -> OPPO

            VIVO.brand -> VIVO

            SAMSUNG.brand -> SAMSUNG

            ZTE.brand -> ZTE

            HTC.brand -> HTC

            GIONEE.brand -> GIONEE

            COOLPAD.brand -> COOLPAD

            else -> UNKNOWN
        }
    }

    /**
     *  get phone OS .
     *  need method parameter { Phone.XIAOMI.cmdProName}
     */
    @SuppressLint("PrivateApi")
    fun getPhoneOs(cmdProName:String): String? {
        val  classType:Class<*>
        var buildVersion: String? = null
        try {
            classType = Class.forName("android.os.SystemProperties")
            val getMethod = classType.getDeclaredMethod("get", String.javaClass)
            buildVersion = getMethod.invoke(classType, cmdProName) as String
        }catch (e:Exception){
            e.printStackTrace()
        }
        return buildVersion
    }

    /**
     * get system language
     */
    fun getSystemLanguage(): String {
        return Locale.getDefault().language
    }

    /**
     * get system version
     */
    fun getSystemVersion(): String {
        return android.os.Build.VERSION.RELEASE
    }

    /**
     * get system model
     */
    fun getSystemModel(): String {
        return android.os.Build.MODEL
    }

    /**
     * get phone num
     */
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getNativePhoneNumber(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.line1Number
    }

    /**
     * get providers name
     */
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getProvidersName(context: Context): String {
        var s1 = "N/A"
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
          val s = telephonyManager.subscriberId
            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
            System.out.println(s)
            if (s.startsWith("46000") || s.startsWith("46002")) {
                s1 = "中国移动"
            } else if (s.startsWith("46001")) {
                s1 = "中国联通"
            } else if (s.startsWith("46003")) {
                s1 = "中国电信"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return s1
    }

    /**
     * get imei
     */
    @SuppressLint("MissingPermission", "HardwareIds")
     fun getImei(context: Context): String {
        val id: String
        //android.telephony.TelephonyManager
        val mTelephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        @Suppress("DEPRECATION")
        if (mTelephony.deviceId != null) {
            id = mTelephony.deviceId
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(context.applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        }
        return id
    }
    /**
     * get mac
     */
    @SuppressLint("HardwareIds", "MissingPermission")
    fun getMacAddress(context: Context): String? {
        var macAddress: String? = null
        val wifiManager =context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifiManager.connectionInfo
        if (!wifiManager.isWifiEnabled) {//open wifi
            wifiManager.isWifiEnabled = true
            wifiManager.isWifiEnabled = false
        }
        if (null != info) {
            macAddress = info.macAddress
        }
        return macAddress
    }

    /**
     *   get  Android Id
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @SuppressLint("HardwareIds")
     fun getAndroidId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

}