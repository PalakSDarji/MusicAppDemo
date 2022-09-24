package com.palak.applemusicappdemo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palak.applemusicappdemo.models.PostingResponse
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.repo.PostingRepo
import com.palak.applemusicappdemo.repo.SongRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This viewModel binds with activity instance, so that it could be
 * shared with different fragment as well. best practice is to have individual viewModel per
 * fragments.
 *
 * Here we allow UI to observe room database table. On initial phase, table will be empty, we call
 * the api to fetch the entries, and add them to room db. Updation will be taken care of automatically
 * using livedata.
 *
 * While dismissing this viewModel, clear out livedata manually for safety
 * even they are lifecycle aware.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepo: SongRepo,
    private val postingRepo: PostingRepo
) : ViewModel() {

    var songLiveData: LiveData<List<SongEntry>>? = songRepo.fetchAllSongEntry()

    private var postingMutableLiveData : MutableLiveData<PostingResponse> = MutableLiveData()
    var postingLiveData : LiveData<PostingResponse> = postingMutableLiveData

    val compositeDisposable = CompositeDisposable()

    fun fetchTopSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            songRepo.checkAndFetch()
        }
    }

    fun getPosts() {
        postingRepo.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PostingResponse> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: PostingResponse) {
                    println("got response 1 : $t")
                    postingMutableLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    println("got error 1 : $e")
                }

                override fun onComplete() {}
            })
    }


    override fun onCleared() {
        super.onCleared()
        songLiveData = null
    }
}