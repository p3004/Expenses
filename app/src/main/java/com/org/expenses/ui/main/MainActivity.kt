package com.org.expenses.ui.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.org.expenses.GetFinancialSms
import com.org.expenses.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val SMS_READ_PERMISSION_CODE = 100
    }

    lateinit var getFinancialSms : GetFinancialSms

    private fun initialize(){
        getFinancialSms = GetFinancialSms()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        askPermissionForReadSms()
    }


   private fun checkPermissionForReadSms(): Boolean = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED

   private fun askPermissionForReadSms(){
        if(!checkPermissionForReadSms())
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS),
            SMS_READ_PERMISSION_CODE
        )
        else
        {
        //    getFinancialSms.getAllSms(this,experimentTv)
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                if(requestCode == SMS_READ_PERMISSION_CODE){
                    if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                 ///       getFinancialSms.getAllSms(this,experimentTv)
                    }else
                    {
                        Toast.makeText(this,"Without sms read permission this app can't run!",Toast.LENGTH_SHORT).show()

                    }

                }

    }

}