package com.test.data.distance.client

import android.content.Context
import com.squareup.moshi.Moshi
import com.test.data.annotations.AppContext
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.model.CustomerResponseModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FileReader @Inject constructor(
    @AppContext private val context: Context,
    private val moshi: Moshi,
    private val fileNameProvider: FileNameProvider
) {

    fun read(): Single<List<CustomerModel>> {
        return Single.fromCallable {
            context.assets.open(fileNameProvider.provideFileName()).bufferedReader()
                .useLines { lines ->
                    val list = ArrayList<CustomerModel>()
                    lines.forEach { customer ->
                        jsonToCustomerObject(customer)?.let { list.add(it.map()) }
                    }
                    list
                }
        }
    }

    private fun jsonToCustomerObject(customer: String): CustomerResponseModel? =
        moshi.adapter(CustomerResponseModel::class.java).fromJson(customer)

}
