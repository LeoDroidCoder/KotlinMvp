package com.leonidustenko.architecture.presentaion.screens.userslist

import com.arellomobile.mvp.InjectViewState
import com.leonidustenko.architecture.data.model.user.User
import com.leonidustenko.architecture.data.repository.user.UserRepository
import com.leonidustenko.architecture.presentaion.base.BasePresenter
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import com.leonidustenko.architecture.presentaion.navigation.Screens
import com.leonidustenko.architecture.presentaion.screens.usersdetails.UserDetailsContract
import com.leonidustenko.architecture.utils.AppSchedulers
import io.reactivex.Flowable
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by leonid on 3/5/18.
 */
@InjectViewState
class UsersListPresenter @Inject constructor(
        private val router: Router?,
        private val repository: UserRepository
) : BasePresenter<UsersListContract.View>(), UsersListContract.Presenter {

    private val users: LinkedHashMap<Long, User> = linkedMapOf()
    private var lastUserId = 0L
    private var currentPage = -1
    private var loading = false
    private var endOfList = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getUsers()
    }

    private fun getUsers() {
        loading = true
        currentPage++
        addDisposable(
                Flowable.defer { repository.getUsersPage(lastUserId) }
                        .observeOn(AppSchedulers.mainThread())
                        .subscribe(
                                {
                                    loading = false
                                    // check whether reached the end
                                    // todo deal with 0 from the web!
                                    endOfList = it.size < repository.getPageSize()
                                    // set last user id
                                    it.lastOrNull()?.let { lastUserId = it.id }
                                    // refresh data set with the fresh portion of users and notify the view
                                    users.putAll(it.map { it.id to it })
                                    viewState?.onUsers(users.values.toList())
                                },
                                {
                                    loading = false
                                    handleError(it, ErrorMessage.ERROR_GET_USERS)
                                }
                        )
        )
    }

    override fun onUserClicked(user: User) {
        router?.navigateTo(Screens.SCREEN_DETAILS, UserDetailsContract.Params(user.id))
    }

    override fun onScrolled(visibleItemCount: Int, totalItemCount: Int, firstVisibleItemPosition: Int) {
        if (shouldLoadMore(visibleItemCount, totalItemCount, firstVisibleItemPosition)) {
            getUsers()
        }
    }

    /**
     * Determines whether new portion of items should be loaded
     */
    private fun shouldLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItemPosition: Int): Boolean {
        return !loading && !endOfList
                && visibleItemCount + firstVisibleItemPosition >= totalItemCount - PAGINATION_MARGIN
                && firstVisibleItemPosition >= 0
                && totalItemCount >= repository.getPageSize()
    }

    override fun onBackPressed() {
        router?.exit()
    }

    private companion object {
        // determines how soon to the end of list load new page
        private const val PAGINATION_MARGIN = 2
    }
}
