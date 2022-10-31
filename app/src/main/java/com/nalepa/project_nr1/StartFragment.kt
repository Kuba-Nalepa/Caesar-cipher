package com.nalepa.project_nr1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.nalepa.project_nr1.databinding.FragmentStartBinding
import com.nalepa.project_nr1.viewmodel.EncryptionViewModel


class StartFragment : Fragment() {

    private val encryptionViewModel: EncryptionViewModel by activityViewModels()
    private var binding: FragmentStartBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_start, container, false)
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = encryptionViewModel
            startFragment = this@StartFragment
        }


    }
    fun setInput()
    {
        encryptionViewModel.setOutputMessage(binding?.inputMessage?.text.toString())
    }

    fun setToggleButton(){
        encryptionViewModel.toggleEncryption(binding!!.toggleIsEncryptionEnabled.isChecked)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}