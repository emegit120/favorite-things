package br.com.threeX.favoritethings.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.threeX.favoritethings.R
import br.com.threeX.favoritethings.data.remote.datasource.UserRemoteFirebaseDataSourceImpl
import br.com.threeX.favoritethings.data.repository.UserRepositoryImpl
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.usecases.CreateUserUseCase
import br.com.threeX.favoritethings.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SignUpFragment : BaseFragment() {

    override val layout = R.layout.cadastro

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(
            this,
            SignUpViewModelFactory(
                CreateUserUseCase(
                    UserRepositoryImpl(
                        UserRemoteFirebaseDataSourceImpl(
                            FirebaseAuth.getInstance(),
                            FirebaseFirestore.getInstance()
                        )
                    )
                )
            )
        ).get(SignUpViewModel::class.java)
    }

    private lateinit var etUserNameSignUp: EditText
    private lateinit var etEmailSignUp: EditText

    private lateinit var etPasswordSignUp: EditText
    private lateinit var btCreateAccount: Button

    private var checkBoxDone = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)

        registerObserver()
    }

    private fun setUpView(view: View) {

        etUserNameSignUp = view.findViewById(R.id.newNome)
        etEmailSignUp = view.findViewById(R.id.newEmail)
        etPasswordSignUp = view.findViewById(R.id.newPass)

        btCreateAccount = view.findViewById(R.id.save)
        setUpListener()
    }

    private fun setUpListener() {


        btCreateAccount.setOnClickListener {
            signUpViewModel.create(
                etUserNameSignUp.text.toString(),
                etEmailSignUp.text.toString(),
                etPasswordSignUp.text.toString()
            )
        }
    }



    private fun registerObserver() {
        this.signUpViewModel.newUserState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.home2)
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
                is RequestState.Loading -> showLoading("Realizando a autenticação")
            }
        })
    }
}

