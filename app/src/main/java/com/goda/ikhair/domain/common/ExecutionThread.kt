package com.goda.ikhair.domain.common

import io.reactivex.Scheduler


interface ExecutionThread {
    val scheduler: Scheduler
}