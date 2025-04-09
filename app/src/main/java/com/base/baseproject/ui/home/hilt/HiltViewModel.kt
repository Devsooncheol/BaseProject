package com.base.baseproject.ui.home.hilt

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
class HiltViewModel @Inject constructor(
    private val hiltFiledInject: HiltFiledInject,
    private val hiltConstructorInjectATypeClass: HiltConstructorInjectATypeClass,
    @HiltProvideModule.HiltModuleInterface2  private val hiltModuleProvideInterface2: HiltModuleProvideInterface,
    private val hiltModuleBindInterface: HiltModuleBindInterface,
) : BaseViewModel() {

    fun test() {
        hiltFiledInject.showTestLog()
        hiltConstructorInjectATypeClass.showTestLog()
        hiltModuleProvideInterface2.showString()
        hiltModuleBindInterface.showString()
    }

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