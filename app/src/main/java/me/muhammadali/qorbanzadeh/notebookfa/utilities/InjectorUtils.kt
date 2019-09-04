package me.muhammadali.qorbanzadeh.notebookfa.utilities

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.muhammadali.qorbanzadeh.notebookfa.data.NoteDatabase
import me.muhammadali.qorbanzadeh.notebookfa.data.NoteRepository
import me.muhammadali.qorbanzadeh.notebookfa.viewmodels.NoteEditorFragmentViewModel
import me.muhammadali.qorbanzadeh.notebookfa.viewmodels.NoteFragmentViewModel

object InjectorUtils {

    fun getNoteRepository(context: Context): NoteRepository {
        return NoteRepository.getInstance(
            NoteDatabase.getInstance(context.applicationContext).noteDao()
        )
    }

    fun provideNoteViewModelFactory(context: Context): NoteViewModelFactory {
        return NoteViewModelFactory(
            getNoteRepository(
                context
            )
        )
    }

    fun provideNoteEditorViewModelFactory(context: Context, noteId: Long): NoteEditorViewModelFactory {
        return NoteEditorViewModelFactory(
            getNoteRepository(
                context
            ), noteId
        )
    }
}

class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NoteFragmentViewModel(noteRepository) as T
    }
}

class NoteEditorViewModelFactory(
    private val noteRepository: NoteRepository,
    private val noteId: Long
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NoteEditorFragmentViewModel(
            noteRepository,
            noteId
        ) as T
    }
}