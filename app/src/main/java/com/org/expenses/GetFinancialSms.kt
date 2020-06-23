package com.org.expenses

import android.content.Context
import android.database.Cursor
import android.os.Build
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast
import com.google.android.material.textview.MaterialTextView
import java.lang.Long
import java.util.*


/**
 * Created by Pallab Banerjee on 6/21/2020.
 */
class GetFinancialSms {


    fun getAllSms(context: Context, tvExperiment: MaterialTextView) {

        val cr = context.contentResolver
        val projectionList: Array<String> = arrayOf(
            Telephony.Sms.DATE,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.TYPE
        )
        val selection = "${Telephony.Sms.TYPE} = ${Telephony.Sms.MESSAGE_TYPE_INBOX}"
        val c: Cursor? = cr.query(Telephony.Sms.CONTENT_URI, projectionList, selection, null, null)
        var allBody = ""
        when (c?.count) {
            null -> {
            }
            0 -> {
                val noSms = "No message to show!"
                tvExperiment.text = noSms
            }
            else -> {
                val totalSMS: Int = c.count
                if (c.moveToFirst()) {
                    for (j in 0 until totalSMS) {
                      /*  val smsDate: String =
                            c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE))
                        val number: String =
                            c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))*/
                        val body: String =
                            c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                      //  val dateFormat = Date(Long.valueOf(smsDate))

                        if (body.contains("credited") || body.contains("debited") || body.contains("withdrawn")) {
                            allBody += body
                        }
                        c.moveToNext()
                    }
                }
                c.close()
                tvExperiment.text = allBody
            }
        }
    }
}