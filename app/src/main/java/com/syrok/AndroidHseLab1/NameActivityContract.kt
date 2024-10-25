package com.syrok.AndroidHseLab1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class NameActivityContract : ActivityResultContract<String, String?>() {
    override fun createIntent(
        context: Context,
        input: String
    ): Intent = Intent(context, NameActivity::class.java).putExtra("greeting", input)

    override fun parseResult(
        resultCode: Int,
        intent: Intent?
    ): String? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra("name")
    }
}