package com.test.spaceapp.ui.fragments.mainFragment


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.github.terrakok.cicerone.Router
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.data.common.Screens.Details
import com.test.spacedemoapp.data.repositories.GetPhotosRxPagingSource
import com.test.spacedemoapp.data.repositories.RoverPhotosRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class MainPresenter @Inject constructor(
    private val roverPhotosRepository: RoverPhotosRepository,
    private val internetStateObservable: Observable<Boolean>,
    private val router: Router
) : MvpPresenter<MainView>() {

    private var isCurrentInternetState: Boolean = false
    private val compositeDisposablePaging = CompositeDisposable()

    private val presenterScope: CoroutineScope by lazy {
        val context: CoroutineContext = Dispatchers.Main.plus(SupervisorJob(null))
        CoroutineScope(context)
    }

    override fun attachView(view: MainView?) {
        super.attachView(view)

        val internetConnectionState = internetStateObservable.subscribe { newInternetState ->
            setInternetAvailable(newInternetState)
        }

        val pagingData = Pager(PagingConfig(pageSize = 25)) {
            GetPhotosRxPagingSource(roverPhotosRepository)
        }.observable.observeOn(AndroidSchedulers.mainThread()).cachedIn(presenterScope)
            .subscribe { pagingData ->  //set it to view
                viewState.hideProgress()
                viewState.setPagingData(pagingData)
            }
        compositeDisposablePaging.add(internetConnectionState)
        compositeDisposablePaging.add(pagingData)
    }

    private fun setInternetAvailable(isAvailable: Boolean) {
        if (!isAvailable) {
            viewState.showInternetConnectionError()
        }
        if (isCurrentInternetState != isAvailable) {
            viewState.resetPhotosList()
        }
        isCurrentInternetState = isAvailable
    }

    fun onBackCommandClick() {
        router.exit()
    }

    fun onForwardCommandClick() {
        router.navigateTo(Details())
    }

    override fun onDestroy() {
        super.onDestroy()
        //todo check - on destroy or ondetach (b - global)
        compositeDisposablePaging.dispose()
    }
}