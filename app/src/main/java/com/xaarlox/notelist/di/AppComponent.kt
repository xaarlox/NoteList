package com.xaarlox.notelist.di

import android.app.Application
import com.xaarlox.notelist.NoteApp
import com.xaarlox.notelist.feature_note.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(app: NoteApp)
    fun inject(activity: MainActivity)
}