package com.goda.emenjoy.domain.common

import io.reactivex.Single

abstract class SingleUseCaseNoParams<out Type> where Type : Any {
    abstract fun build(): Single<out Type>
}