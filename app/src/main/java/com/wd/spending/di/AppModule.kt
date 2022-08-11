package com.wd.spending.di

import android.content.Context
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
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
    fun provideSpendingRepository(spreadSheetDataSource: SpreadSheetDataSource): SpendingRepository {
        return SpendingRepository(spreadSheetDataSource)
    }

    @Provides
    @Singleton
    fun provideSpreadSheetDataSource(googleAccountCredential: GoogleAccountCredential): SpreadSheetDataSource {
        return SpreadSheetDataSource(googleAccountCredential)
    }
}