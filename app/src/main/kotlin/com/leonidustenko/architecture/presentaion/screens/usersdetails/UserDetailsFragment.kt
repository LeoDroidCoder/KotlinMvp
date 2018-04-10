package com.leonidustenko.architecture.presentaion.screens.usersdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.leonidustenko.architecture.R
import com.leonidustenko.architecture.presentaion.App
import com.leonidustenko.architecture.presentaion.base.BaseFragment
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject

/**
 * Created by leonid on 3/5/18.
 */
class UserDetailsFragment : BaseFragment<UserDetailsContract.Presenter>(), UserDetailsContract.View {

    @Inject
    @InjectPresenter
    lateinit var presenter: UserDetailsPresenter

    companion object {

        private const val ARGUMENT_USER_LOGIN = "ARGUMENT_USER_LOGIN"

        fun newInstance(params: UserDetailsContract.Params) = UserDetailsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ARGUMENT_USER_LOGIN, params.userLogin)
                    }
                }
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARGUMENT_USER_LOGIN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    @ProvidePresenter
    override fun providePresenter(): UserDetailsPresenter = presenter

    override fun onError(errorCode: ErrorMessage) {
        super.handleError(errorCode)
    }

    private fun injectDependencies() {
        App.appComponent.inject(this)
    }

    private fun setupViews() {
        //todo
        tv_login?.text = userLogin.toString()

    }
}
