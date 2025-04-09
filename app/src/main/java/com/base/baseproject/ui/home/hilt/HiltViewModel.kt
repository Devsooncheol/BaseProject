package com.base.baseproject.ui.home.hilt

import android.content.Intent
import com.base.baseproject.ui.home.hilt.sample.HiltConstructorInjectATypeClass
import com.base.baseproject.ui.home.hilt.sample.HiltFiledInject
import com.base.baseproject.ui.home.hilt.sample.TestInterface
import com.base.baseproject.ui.home.hilt.sample.TestModule
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(
    private val hiltFiledInject: HiltFiledInject,
    private val hiltConstructorInjectATypeClass: HiltConstructorInjectATypeClass,
    @TestModule.TestInterface2  private val testInterface2: TestInterface,
) : BaseViewModel() {

    fun test() {
        hiltFiledInject.showTestLog()
        hiltConstructorInjectATypeClass.showTestLog()
        testInterface2.showString()
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