package com.leonidustenko.architecture.presentaion.screens.container

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.leonidustenko.architecture.R
import com.leonidustenko.architecture.presentaion.App
import com.leonidustenko.architecture.presentaion.base.BaseActivity
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import com.leonidustenko.architecture.presentaion.navigation.NavigatorFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ContainerActivity : BaseActivity<ContainerPresenter>(), ContainerContract.View {

    @Inject
    @InjectPresenter
    lateinit var presenter: ContainerPresenter

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private val _navigator: Navigator by lazy {
        NavigatorFactory.create(supportFragmentManager, this, R.id.fl_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
    }

    private fun injectDependencies() {
        App.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        cicerone.navigatorHolder.setNavigator(_navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    @ProvidePresenter
    override fun providePresenter(): ContainerPresenter = presenter


    override fun onError(errorCode: ErrorMessage) {
        super.handleError(errorCode)
    }
}
