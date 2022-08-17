package com.wd.spending.di

import android.content.Context
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.wd.spending.data.SpendingRepository
import com.wd.spending.data.remote.SpreadSheetDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGoogleAccountCredential(@ApplicationContext appContext: Context): GoogleAccountCredential {
        return GoogleAccountCredential.usingOAuth2(appContext, setOf(SheetsScopes.SPREADSHEETS))
    }

    @Provides
    @Singleton
    fun provideSheetService(googleAccountCredential: GoogleAccountCredential): Sheets {
        val transport = AndroidHttp.newCompatibleTransport()
        val jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()
        return Sheets.Builder(transport, jsonFactory, googleAccountCredential)
            .setApplicationName("Google Sheets").build()
    }

    @Provides
    @Singleton
    fun provideSpendingRepository(spreadSheetDataSource: SpreadSheetDataSource): SpendingRepository {
        return SpendingRepository(spreadSheetDataSource)
    }

    @Provides
    @Singleton
    fun provideSpreadSheetDataSource(sheetService: Sheets): SpreadSheetDataSource {
        return SpreadSheetDataSource(sheetService)
    }
}