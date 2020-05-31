package com.test.data.distance.client

const val FILE_CUSTOMERS = "customers.txt"

class FileNameProvider {
    fun provideFileName(): String = FILE_CUSTOMERS
}