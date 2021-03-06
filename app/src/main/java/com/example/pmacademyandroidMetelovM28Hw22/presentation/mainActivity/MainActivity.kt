package com.example.pmacademyandroidMetelovM28Hw22.presentation.mainActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pmacademyandroidMetelovM28Hw22.App
import com.example.pmacademyandroidMetelovM28Hw22.R
import com.example.pmacademyandroidMetelovM28Hw22.databinding.ActivityMainBinding
import com.example.pmacademyandroidMetelovM28Hw22.presentation.feedFragment.ShowAllPostsFragment
import com.example.pmacademyandroidMetelovM28Hw22.tools.UpdatingState
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupInjection()
        updateLocalStorage()
        observeError()
        setupBinding()
        setupBasicFragment()

    }

    private fun setupInjection() {
        val app = this.application as App
        app.getComponent().inject(this)
    }

    private fun updateLocalStorage() {
        viewModel.updateRepo()
    }

    private fun observeError() {
        viewModel.errorLiveData.observe(this, {
            it?.let {
                when (it) {
                    UpdatingState.ERROR -> showError()
                    UpdatingState.COMPLETED -> showSuccess()
                    UpdatingState.LOADING -> {
                    }
                }
            }
        })
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupBasicFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_content_fragment, ShowAllPostsFragment.newInstance())
            .commit()
    }

    private fun showError() {
        Toast.makeText(this, R.string.error_text, Toast.LENGTH_LONG).show()
    }

    private fun showSuccess() {
        Toast.makeText(this, R.string.success_text, Toast.LENGTH_LONG).show()
    }
}
