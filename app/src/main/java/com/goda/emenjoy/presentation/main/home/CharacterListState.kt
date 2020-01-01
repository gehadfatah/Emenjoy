package com.goda.emenjoy.presentation.main.home

import com.goda.emenjoy.model.data_model.Video


sealed class CharacterListState {
    abstract val pageNum: Int
    abstract val loadedAllItems: Boolean
    abstract val data: List<Video>
}

data class DefaultState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<Video>) : CharacterListState()
data class LoadingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<Video>) : CharacterListState()
data class PaginatingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<Video>) : CharacterListState()
data class ErrorState(val error: Throwable, override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<Video>) : CharacterListState()