package com.wd.spending.data

import com.wd.spending.data.remote.SpreadSheetDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpendingRepository @Inject constructor(private val spreadSheetDataSource: SpreadSheetDataSource) {

    suspend fun fetchPaymentAndSpend(): Flow<List<List<String>>> = flow {
        val info = spreadSheetDataSource.fetchPaymentAndSpend()
        emit(info)
        return@flow
    }
}