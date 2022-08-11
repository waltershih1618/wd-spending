package com.wd.spending.data

import com.wd.spending.data.remote.SpreadSheetDataSource
import javax.inject.Inject

class SpendingRepository @Inject constructor(
    private val spreadSheetDataSource: SpreadSheetDataSource
) {
}