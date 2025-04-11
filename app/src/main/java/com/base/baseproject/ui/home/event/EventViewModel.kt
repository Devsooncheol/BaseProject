package com.base.baseproject.ui.home.event

import android.content.Intent
import com.base.baseproject.ui.home.hilt.sample.HiltConstructorInjectATypeClass
import com.base.baseproject.ui.home.hilt.sample.HiltFiledInject
import com.base.baseproject.ui.home.hilt.sample.HiltModuleBindInterface
import com.base.baseproject.ui.home.hilt.sample.HiltModuleProvideInterface
import com.base.baseproject.ui.home.hilt.sample.HiltProvideModule
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
) : BaseViewModel() {

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