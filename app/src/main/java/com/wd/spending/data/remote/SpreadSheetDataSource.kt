package com.wd.spending.data.remote

import com.google.api.services.sheets.v4.Sheets
import com.wd.spending.SHEET_RAGE_PAYMENT
import com.wd.spending.SHEET_RANGE_SPEND
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpreadSheetDataSource @Inject constructor(private val sheetService: Sheets) {

    val SPREADSHEET_ID = ""

    suspend fun fetchPaymentAndSpend(): List<List<String>> = withContext(Dispatchers.IO) {
        val response = sheetService.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(
            listOf(SHEET_RANGE_SPEND, SHEET_RAGE_PAYMENT)
        ).execute()

        listOf(
            response.valueRanges[0].getValues().map { it.toString() },
            response.valueRanges[1].getValues().toList().map { it.toString() })
    }
}