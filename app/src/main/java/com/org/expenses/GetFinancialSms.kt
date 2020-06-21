package com.org.expenses

import android.content.Context
import android.database.Cursor
import android.provider.Telephony
import android.widget.Toast
import com.google.android.material.textview.MaterialTextView
import java.lang.Long
import java.util.*


/**
 * Created by Pallab Banerjee on 6/21/2020.
 */
class GetFinancialSms {


    fun getAllSms(context: Context,tvExperiment : MaterialTextView) {
        val cr = context.contentResolver
        val c: Cursor? = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
        var totalSMS = 0
        var allBody = ""
        if (c != null) {
            totalSMS = c.count
            if (c.moveToFirst()) {
                for (j in 0 until totalSMS) {
                    val smsDate: String =
                        c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE))
                    val number: String =
                        c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body: String =
                        c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val dateFormat = Date(Long.valueOf(smsDate))

                    if (c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)).toInt() == Telephony.Sms.MESSAGE_TYPE_INBOX) {

                        if(body.contains("credited")|| body.contains("debited") || body.contains("withdrawn")) {
                           allBody += body
                        }
                    }
                    c.moveToNext()
                }
            }
            c.close()
            tvExperiment.text = allBody
        } else {
            Toast.makeText(context, "No message to show!", Toast.LENGTH_SHORT).show()
        }
    }

}