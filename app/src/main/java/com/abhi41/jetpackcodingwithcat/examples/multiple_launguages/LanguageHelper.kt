package com.abhi41.jetpackcodingwithcat.examples.multiple_launguages

import android.content.Context
import android.content.ContextWrapper
import java.util.Locale

class LanguageHelper {

    companion object {
        fun changeLanguage(context: Context,languageCode: String): ContextWrapper{
            var ctx = context
            val resouces = ctx.resources
            val configuration = resouces.configuration
            val systemLocale = configuration.locales[0]

            if (!languageCode.equals("") && languageCode != systemLocale.language){
                val locale = Locale(languageCode)
                Locale.setDefault(locale)
                configuration.setLocale(locale)
                ctx = ctx.createConfigurationContext(configuration)
            }
            return ContextWrapper(ctx)
        }
    }

}