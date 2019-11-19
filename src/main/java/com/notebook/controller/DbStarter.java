package com.notebook.controller;

import com.notebook.model.Note;
import com.notebook.model.Notebook;
import com.notebook.repository.NoteRepository;
import com.notebook.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbStarter implements CommandLineRunner {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {

        notebookRepository.deleteAll();
        noteRepository.deleteAll();
        Notebook notebook=new Notebook();
        notebook.setName("Default Notebook");
        Note note=new Note();
        note.setTitel("default title");
        note.setText("defualt text");
        note.setNotebook(notebook);

        notebookRepository.save(notebook);
        noteRepository.save(note);



    }
}
