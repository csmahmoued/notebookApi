package com.notebook.mapper;

import com.notebook.model.Note;
import com.notebook.model.Notebook;
import com.notebook.modelView.NoteModeView;
import com.notebook.modelView.NotebookModelView;
import com.notebook.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private NotebookRepository notebookRepository;

    public NoteModeView covertToNoteModelView(Note note){
        var noteEntity=new NoteModeView();
        noteEntity.setNoteId(String.valueOf(note.getId()));
        noteEntity.setTitle(note.getText());
        noteEntity.setText(note.getText());
        noteEntity.setLastModeification(note.getLastmodification());
        noteEntity.setNotebookId(String.valueOf(note.getNotebook().getId()));
        return  noteEntity;
    }

    public NotebookModelView covertNoteBookModeView(Notebook notebook){
        var bookNoteEntity = new NotebookModelView();
        bookNoteEntity.setId(String.valueOf(notebook.getId()));
        bookNoteEntity.setName(notebook.getName());
        bookNoteEntity.setNbnote(notebook.getNotes().size());
        return bookNoteEntity;
    }

    public Note covertNoteToEntity(NoteModeView modeView){
        var notebook=notebookRepository.findById(Integer.parseInt(modeView.getNotebookId())).get();
        var model=new Note();
        model.setId(Integer.parseInt(modeView.getNoteId()));
        model.setLastmodification(modeView.getLastModeification());
        model.setNotebook(notebook);
        model.setText(modeView.getText());
        model.setTitel(modeView.getText());
        return  model;
    }

    public Notebook covertToNoteBookEntity(NotebookModelView modelView){
        var model=new Notebook();
        model.setId(Integer.parseInt(modelView.getId()));
        model.setName(modelView.getName());
        return model;
    }




}
