package com.test.data.base.usecase

import com.test.data.base.model.MappedModel
import io.reactivex.Single

interface NoArgUseCase<R : MappedModel> {
    fun get(): Single<R>
}