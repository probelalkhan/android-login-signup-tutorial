package net.simplifiedcoding.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.network.UserApi
import net.simplifiedcoding.data.repository.UserRepository
import net.simplifiedcoding.data.responses.LoginResponse
import net.simplifiedcoding.data.responses.User
import net.simplifiedcoding.databinding.FragmentHomeBinding
import net.simplifiedcoding.ui.base.BaseFragment
import net.simplifiedcoding.ui.visible


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}