package br.com.threeX.favoritethings.presentation.base.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import br.com.threeX.favoritethings.R
import br.com.threeX.favoritethings.data.remote.datasource.UserRemoteFirebaseDataSourceImpl
import br.com.threeX.favoritethings.data.repository.UserRepositoryImpl
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase
import br.com.threeX.favoritethings.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val NAVIGATION_KEY = "NAV_KEY"
@ExperimentalCoroutinesApi
abstract class BaseAuthFragment : BaseFragment() {

    private val baseAuthViewModel: BaseAuthViewModel by lazy {
        ViewModelProvider(
            this,
            BaseAuthViewModelFactory(GetUserLoggedUseCase(UserRepositoryImpl(
                UserRemoteFirebaseDataSourceImpl(
                    FirebaseAuth.getInstance(),
                    FirebaseFirestore.getInstance()
                )
            )))
        )[BaseAuthViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registerObserver()
        baseAuthViewModel.getUserLogged()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun registerObserver() {

        baseAuthViewModel.userLogged.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is RequestState.Loading -> {
                    showLoading()
                }

                is RequestState.Success -> {
                    hideLoading()
                }

                is RequestState.Error -> {
                    findNavController(this).navigate(
                        R.id.login_graph, bundleOf(
                            NAVIGATION_KEY to findNavController(this).currentDestination?.id
                        )
                    )

                    hideLoading()
                }
            }
        })
    }
}

