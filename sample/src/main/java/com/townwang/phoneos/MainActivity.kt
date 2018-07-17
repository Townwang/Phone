package com.townwang.phoneos
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.townwang.phone.PhoneUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var  CODE = 1200
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if( ContextCompat.checkSelfPermission(baseContext,android.Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED){//没有权限需要动态获取
            //动态请求权限
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE),CODE)

        }else {
            setText()
        }

    }

    @SuppressLint("SetTextI18n")
    fun setText(){
        val  phoneModel = PhoneUtils.getPhone()
        textView.text =
                " phone  :::" + phoneModel.brand+
                "\n MAC Address   :::"+PhoneUtils.getMacAddress(baseContext)+
                "\n OS  :::"+PhoneUtils.getPhoneOs(phoneModel.cmdPropName)+
                "\n SystemVersion  :::"+PhoneUtils.getSystemVersion()+
                "\n Providers :::"+PhoneUtils.getProvidersName(baseContext)+
                "\n imei :::"+PhoneUtils.getImei(baseContext)+
                "\n AndroidId :::"+PhoneUtils.getAndroidId(baseContext)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//判断是否给于权限
              setText()
            } else {
                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
