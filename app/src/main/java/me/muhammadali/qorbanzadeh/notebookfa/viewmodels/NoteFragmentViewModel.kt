package me.muhammadali.qorbanzadeh.notebookfa.viewmodels

import android.util.Log
import androidx.lifecycle.*
import me.muhammadali.qorbanzadeh.notebookfa.adapters.NoteListAdapter
import me.muhammadali.qorbanzadeh.notebookfa.data.Note
import me.muhammadali.qorbanzadeh.notebookfa.data.NoteRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NoteFragmentViewModel(private val repository: NoteRepository) : ViewModel() {

    val adapter = NoteListAdapter()
    val notes = repository.getAllNotes()

    fun insertNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun insertNotes(vararg notes: Note) {
        viewModelScope.launch {
            repository.insertNotes(*notes)
        }
    }

    fun insertListOfNotes(list: List<Note>) {
        viewModelScope.launch {
            repository.insertListOfNotes(list)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        Log.i(this.javaClass.simpleName, "onCleared()")
        viewModelScope.cancel()
    }
}