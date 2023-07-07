package com.abhi41.jetpackcodingwithcat.examples.multiple_launguages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import com.abhi41.jetpackcodingwithcat.R
import com.abhi41.jetpackcodingwithcat.databinding.ActivityMultiLanguageBinding
import com.abhi41.jetpackcodingwithcat.databinding.FragmentLanguagePopupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



class LanguagePopupFrag(
    private val listener: LanguageDialogClickListener
) : BottomSheetDialogFragment() {

    companion object{
        const val TAG = "LanguagePopupFrag"
    }

    private lateinit var binding: FragmentLanguagePopupBinding
    var selectedLang: String = ""
    var selectedLangCode: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLanguagePopupBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (selectedLang) {
            resources.getString(R.string.english) -> {
                binding.radEnglish.isChecked = true
            }

            resources.getString(R.string.hindi) -> {
                binding.radHindi.isChecked = true
            }

            resources.getString(R.string.marathi) -> {
                binding.radMarathi.isChecked = true
                selectedLangCode = "mr"
            }
        }
        binding.langRadioGroup.setOnCheckedChangeListener { p0, itemId ->
            when (itemId) {
                R.id.radEnglish -> {
                    selectedLang = resources.getString(R.string.english)
                    selectedLangCode = "en"
                }

                R.id.radHindi -> {
                    selectedLang = resources.getString(R.string.hindi)
                    selectedLangCode = "hi"
                }

                R.id.radMarathi -> {
                    selectedLang = resources.getString(R.string.marathi)
                    selectedLangCode = "mr"
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            Log.d(TAG, "selectedLang: $selectedLang")
            Log.d(TAG, "selectedLangCode: $selectedLangCode")
            listener.submitBtnClicked(AppLanguage(selectedLang,selectedLangCode))
        }
    }

    interface LanguageDialogClickListener {
        fun submitBtnClicked(appLanguage: AppLanguage)
    }
}