package com.base.baseproject.ui.launcher

import android.content.Intent
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import timber.log.Timber

class LauncherViewModel(): BaseViewModel() {


    override fun getIntentExtraNext(intent: Intent): Intent {
        Timber.d("getIntentExtraNext, tag : $TAG")
        intent.putExtra(BaseConstants.INTENT_EXTRA_NEXT,TAG)
        return intent
    }

    override fun getIntentExtraFirst(intent: Intent): Intent {
        Timber.d("getIntentExtraFirst, tag : $TAG")
        intent.putExtra(BaseConstants.INTENT_EXTRA_FIRST,TAG)
        return intent
    }
}