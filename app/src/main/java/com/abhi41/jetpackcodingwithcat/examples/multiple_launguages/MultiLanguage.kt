package com.abhi41.jetpackcodingwithcat.examples.multiple_launguages

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.abhi41.jetpackcodingwithcat.R
import com.abhi41.jetpackcodingwithcat.databinding.ActivityMultiLanguageBinding
import com.abhi41.jetpackcodingwithcat.examples.coroutine.series.lifecyclerScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MultiLanguage : AppCompatActivity(), LanguagePopupFrag.LanguageDialogClickListener {
    private lateinit var binding: ActivityMultiLanguageBinding
    private lateinit var dialogFrag: LanguagePopupFrag
    private lateinit var settingManager: SettingManager
    private var selectedLang = ""
    private var selectedLangCode = ""

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let { context ->
            settingManager = SettingManager(context)
            val appLanguage = settingManager.currentLang
            selectedLang = appLanguage.selectedLang
            selectedLangCode = appLanguage.selectedLangCode
            LanguageHelper.changeLanguage(context, selectedLangCode)
        }
        super.attachBaseContext(newBase)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogFrag = LanguagePopupFrag(this)
        binding.btnChangeLang.setOnClickListener {
            if (!dialogFrag.isAdded) {
                dialogFrag.selectedLang = selectedLang
                dialogFrag.selectedLangCode = selectedLangCode
                dialogFrag.show(supportFragmentManager, LanguagePopupFrag.TAG)
            }
        }
        binding.imgNext.setOnClickListener {
            startActivity(Intent(this,ItemActivity::class.java))
        }

        observerLanguageChanges()
    }

    private fun observerLanguageChanges() {
        lifecycleScope.launch {
            settingManager.observerLanguageChanges().collect{
                if (selectedLangCode != it.selectedLangCode){
                    restartActivity()
                }
            }
        }
    }

    private fun restartActivity(){
        startActivity(Intent(this, MultiLanguage::class.java))
        overridePendingTransition(0,0)
        finish()
        overridePendingTransition(0,0)
    }

    override fun submitBtnClicked(appLanguage: AppLanguage) {
        if (dialogFrag.isAdded){
            dialogFrag.dismiss()
        }

        if (selectedLangCode != appLanguage.selectedLangCode){
            lifecycleScope.launch {
                settingManager.saveSelectedLanguage(appLanguage)
            }
        }

    }

}