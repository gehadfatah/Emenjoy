package com.goda.emenjoy.domain.common

import io.reactivex.Scheduler


interface ExecutionThread {
    val scheduler: Scheduler
}