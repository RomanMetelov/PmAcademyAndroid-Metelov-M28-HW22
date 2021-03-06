package com.example.pmacademyandroidMetelovM28Hw22.presentation.newPostFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmacademyandroidMetelovM28Hw22.App
import com.example.pmacademyandroidMetelovM28Hw22.databinding.CreateNewPostFragmentBinding
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.VerificationStatus
import com.example.pmacademyandroidMetelovM28Hw22.tools.hideKeyboard
import javax.inject.Inject

class CreateNewPostFragment : Fragment() {

    @Inject
    lateinit var viewModel: CreateNewPostViewModel
    private lateinit var binding: CreateNewPostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateNewPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as App
        app.getComponent().inject(this)

        setupListeners()
        observeErrorInput()
    }

    private fun setupListeners() {
        binding.btnAddNewPost.setOnClickListener {
            sendDataToBack()
        }
    }

    private fun observeErrorInput() {
        viewModel.stringErrorLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is VerificationStatus.Normal -> {
                    closeCurrentFragment()
                    this.hideKeyboard()
                }
                is VerificationStatus.Error -> {
                    binding.tvInputErrors.text = it.errors
                }
            }
        }
    }

    private fun sendDataToBack() {
        viewModel.sendDataToCache(binding.etTitle.text.toString(), binding.etBody.text.toString())
    }

    private fun closeCurrentFragment() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    companion object {
        fun newInstance() = CreateNewPostFragment()
    }
}
