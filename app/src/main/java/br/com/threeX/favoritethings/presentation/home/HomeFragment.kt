package br.com.threeX.favoritethings.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.threeX.favoritethings.R
import br.com.threeX.favoritethings.data.remote.datasource.AppRemoteFirebaseDataSourceImpl
import br.com.threeX.favoritethings.data.remote.datasource.UserRemoteFirebaseDataSourceImpl
import br.com.threeX.favoritethings.data.repository.AppRepositoryImpl
import br.com.threeX.favoritethings.data.repository.UserRepositoryImpl
import br.com.threeX.favoritethings.domain.entity.DashboardItem
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.usecases.GetDashboardMenuUseCase
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase
import br.com.threeX.favoritethings.extensions.startDeeplink
import br.com.threeX.favoritethings.presentation.base.auth.BaseAuthFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragment : BaseAuthFragment() {

    override val layout = R.layout.activity_splash

    private lateinit var tvHomeHelloUser: TextView
    private lateinit var rvHomeDashboard: RecyclerView

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModelFactory(
                GetDashboardMenuUseCase(
                    AppRepositoryImpl(
                        AppRemoteFirebaseDataSourceImpl()
                    )
                ),
                GetUserLoggedUseCase(
                    UserRepositoryImpl(
                        UserRemoteFirebaseDataSourceImpl(
                            FirebaseAuth.getInstance(),
                            FirebaseFirestore.getInstance()
                        )
                    )
                )
            )
        )[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBackPressedAction()

        setUpView(view)

        registerObserver()
        homeViewModel.getDashboardMenu()
    }

    private fun setUpView(view: View) {
        rvHomeDashboard = view.findViewById(R.id.textView)
        tvHomeHelloUser = view.findViewById(R.id.textView)
    }

    private fun registerObserver() {
        homeViewModel.dashboardItemsState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Loading -> {
                    showLoading()
                }

                is RequestState.Success -> {
                    setUpMenu(it.data)
                    hideLoading()
                }

                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
            }
        })

        homeViewModel.headerState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is RequestState.Loading -> {
                    tvHomeHelloUser.text = "Carregando o usuario"
                }

                is RequestState.Success -> {
                    val (title, userName) = it.data
                    tvHomeHelloUser.text = String.format(title, userName)
                    hideLoading()
                }

                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
            }
        })

    }

    private fun setUpMenu(items: List<DashboardItem>) {
        rvHomeDashboard.adapter = HomeAdapter(items, this::clickItem)
    }

    private fun clickItem(item: DashboardItem) {
        item.onDisabledListener.let {
            it?.invoke(requireContext())
        }

        if (item.onDisabledListener == null) {
            when (item.feature) {
                "SIGN_OUT" -> {
                    //chamar o metodo de logout
                }

                "ETHANOL_OR_GASOLINE" -> {
                    startDeeplink("${item.action.deeplink}?id=${homeViewModel.userLogged?.id}")
                }

                else -> {
                    startDeeplink(item.action.deeplink)
                }

            }
        }
    }

    private fun registerBackPressedAction() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}
