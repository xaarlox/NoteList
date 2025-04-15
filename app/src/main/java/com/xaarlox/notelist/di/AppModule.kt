package com.xaarlox.notelist.di

import android.app.Application
import androidx.room.Room
import com.xaarlox.notelist.feature_note.data.data_source.NoteDatabase
import com.xaarlox.notelist.feature_note.data.repository.NoteRepositoryImpl
import com.xaarlox.notelist.feature_note.domain.repository.NoteRepository
import com.xaarlox.notelist.feature_note.domain.use_case.DeleteNote
import com.xaarlox.notelist.feature_note.domain.use_case.GetNotes
import com.xaarlox.notelist.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository)
        )
    }
}