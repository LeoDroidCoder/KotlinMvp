package com.leonidustenko.architecture.presentaion.screens.userslist

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import com.leonidustenko.architecture.R
import com.leonidustenko.architecture.data.model.user.User
import com.leonidustenko.architecture.presentaion.App
import com.leonidustenko.architecture.presentaion.base.BaseFragment
import com.leonidustenko.architecture.presentaion.common.adapter.UsersAdapter
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject

/**
 * Created by leonid on 3/5/18.
 */
class UsersListFragment : BaseFragment<UsersListContract.Presenter>(), UsersListContract.View, OnRecyclerItemClickListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: UsersListPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UsersAdapter

    companion object {
        fun newInstance() = UsersListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    @ProvidePresenter
    override fun providePresenter(): UsersListPresenter = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onUsers(users: List<User>) {
        adapter.updateItems(users)
        // todo handle asynchronous data loading
        setEmptyViewAppearance(adapter.isEmpty, false)
    }

    private fun injectDependencies() {
        App.appComponent.inject(this)
    }

    private fun setupViews() {
        adapter = UsersAdapter(context, this)
        layoutManager = LinearLayoutManager(context)
        rv_users?.adapter = adapter
        rv_users?.layoutManager = layoutManager
        rv_users?.setHasFixedSize(true)
        rv_users?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rv_users?.addOnScrollListener(scrollListener)
    }

    override fun onItemClick(position: Int) {
        presenter.onUserClicked(adapter.getItem(position))
    }

    override fun onError(errorCode: ErrorMessage) {
        super.handleError(errorCode)
        setEmptyViewAppearance(adapter.isEmpty, true)
    }

    /**
     * Sets empty view and recycler appearance according to whether there ara any items or not.
     * In case it is empty, shows an empty view with the corresponding message.
     *
     * @param isEmpty `true` if adapter is empty
     * @param isError `true` if an error occurred
     */
    private fun setEmptyViewAppearance(isEmpty: Boolean, isError: Boolean) {
        rv_users?.visibility = if (isEmpty) View.GONE else View.VISIBLE
        tv_empty?.visibility = if (!isEmpty) View.GONE else View.VISIBLE
        if (isEmpty) setEmptyViewMessage(isError)
    }

    /**
     * Sets an empty view message according to whether it is caused by error or not
     *
     * @param isError set error message in case of `true` or empty message in case of `false`
     */
    private fun setEmptyViewMessage(isError: Boolean) {
        tv_empty?.text = getString(if (isError) R.string.error_message_get_users else R.string.text_empty_users)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            presenter.onScrolled(layoutManager.childCount, layoutManager.itemCount,
                    layoutManager.findFirstVisibleItemPosition())
        }
    }
}
