package com.shannan.moziopizza.base

import com.shannan.moziopizza.base.presentation.nav.NavManager
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}
