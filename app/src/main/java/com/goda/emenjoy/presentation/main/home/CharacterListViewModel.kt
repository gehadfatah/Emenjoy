package com.goda.emenjoy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.goda.emenjoy.domain.CauseListUseCases
import com.goda.emenjoy.domain.causeListUseCasesDep
import com.goda.emenjoy.domain.common.addTo
import com.goda.emenjoy.model.common.ApplicationIntegration
import com.goda.emenjoy.model.data_model.Ad
import com.goda.emenjoy.model.data_model.EmenjoyResponse
import com.goda.emenjoy.model.data_model.Slider
import com.goda.emenjoy.model.data_model.Video
import com.goda.emenjoy.presentation.common.BaseViewModel
import com.goda.emenjoy.presentation.common.androidMainThreadScheduler
import com.goda.emenjoy.presentation.common.schedulerIo
import eg.naqaaee.core.data.AppDatabase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


private val TAG = CharacterListViewModel::class.java.name

class CharacterListViewModel(private val characterListUseCases: CauseListUseCases = causeListUseCasesDep, private val subscribeOnScheduler: Scheduler = schedulerIo, private val observeOnScheduler: Scheduler = androidMainThreadScheduler) : BaseViewModel() {

    val stateLiveData = MutableLiveData<CharacterListState>()
    val videosDataLiveData = MutableLiveData<List<Video>>()
    val adLiveData = MutableLiveData<Ad>()
    val sliderLiveData = MutableLiveData<ArrayList<Slider>>()

    init {
        stateLiveData.value = DefaultState(1, false, emptyList())
    }

    fun updateCryptoList() {
        val pageNum = obtainCurrentPageNum()
        stateLiveData.value = if (pageNum == 1)
            LoadingState(pageNum, false, obtainCurrentData())
        else
            PaginatingState(pageNum, false, obtainCurrentData())
        getCryptoList(pageNum)
    }

    fun resetCryptoList() {
        stateLiveData.value = LoadingState(1, false, emptyList())
        updateCryptoList()
    }

    fun restoreCryptoList() {
        val pageNum = obtainCurrentPageNum()
        stateLiveData.value = DefaultState(pageNum, false, obtainCurrentData())
    }

    private fun getCryptoList(page: Int) {
        characterListUseCases.getEnjoyVideos(page)

                /* .map { it.filterNot { character -> character.thumbnail.path.contains("image_not_available") } }
                */.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onCryptoListReceived, this::onError).addTo(disposable)
    }

    private fun onCryptoListReceived(emenjoyResponse: EmenjoyResponse) {
        val currentCharacterList = obtainCurrentData().toMutableList()
        val currentPageNum = obtainCurrentPageNum() + 1
        val areAllItemsLoaded = /*cryptoList.size < LIMIT_Character_LIST*/ emenjoyResponse.data.videos.next_page.isNullOrEmpty()
        currentCharacterList.addAll(emenjoyResponse.data.videos.videos)
        //if (sliderLiveData.value.isNullOrEmpty())
        if (sliderLiveData.value == null) {
            sliderLiveData.value = ArrayList(emenjoyResponse.data.slider)
        } else {
            sliderLiveData.value?.addAll(emenjoyResponse.data.slider)
            sliderLiveData.value = sliderLiveData.value
        }

        // sliderLiveData.value = emenjoyResponse.data.slider
        adLiveData.value = emenjoyResponse.data.videos.ad
        stateLiveData.value = DefaultState(currentPageNum, areAllItemsLoaded, currentCharacterList)

        insertVideos(emenjoyResponse.data.videos.videos)
    }

    private fun insertVideos(videos: List<Video>) {

        characterListUseCases.insertVideo(AppDatabase.getDatabase(ApplicationIntegration.getApplication()
        ).videoDao(), videos = videos)
    }


    private fun onError(error: Throwable) {
        val pageNum = stateLiveData.value?.pageNum ?: 1
        stateLiveData.value = ErrorState(error, pageNum, obtainCurrentLoadedAllItems(), obtainCurrentData())
    }

    private fun obtainCurrentPageNum() = stateLiveData.value?.pageNum ?: 1

    private fun obtainCurrentData() = stateLiveData.value?.data ?: emptyList()

    private fun obtainCurrentLoadedAllItems() = stateLiveData.value?.loadedAllItems ?: false
    fun setupdateVideoInDatabase(v: Video) {
        characterListUseCases.updateVideo(AppDatabase.getDatabase(ApplicationIntegration.getApplication()
        ).videoDao(), v)

    }
    fun getvideosDataLiveData() {
        characterListUseCases.getVideosListData(AppDatabase.getDatabase(ApplicationIntegration.getApplication()
        ).videoDao())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isLoading.value = true
                }
                .subscribe({
                    videosDataLiveData.value = ArrayList(it)
                }, {
                    error.value = it
                    videosDataLiveData.value = ArrayList()
                })
                .addTo(disposable)

    }

}