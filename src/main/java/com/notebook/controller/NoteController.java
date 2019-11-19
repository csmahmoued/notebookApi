package com.notebook.controller;

import com.notebook.mapper.Mapper;
import com.notebook.model.Note;
import com.notebook.model.Notebook;
import com.notebook.modelView.NoteModeView;
import com.notebook.repository.NoteRepository;
import com.notebook.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/note")
@CrossOrigin
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<NoteModeView>> getAll(){
        var notes=noteRepository.findAll();
        var noteModel=notes.stream().map(n->mapper.covertToNoteModelView(n)).collect(Collectors.toList());
        return new ResponseEntity<>(noteModel,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<NoteModeView> getNote(@PathVariable int id){
        var note=noteRepository.findById(id).get();
        var model =mapper.covertToNoteModelView(note);
        return new ResponseEntity<>(model,HttpStatus.OK);
    }

    @GetMapping("/notebook/{notebookid}")
    public  ResponseEntity<List<NoteModeView>> getNoteBooks(@PathVariable int notebookid){
        List<Note> notes = new ArrayList<>();

        var notebook = this.notebookRepository.findById(notebookid);
        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }

        // map to note view model
        var notesViewModel = notes.stream()
                .map(note -> this.mapper.covertToNoteModelView(note))
                .collect(Collectors.toList());
            if(notesViewModel.size()==0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        return new ResponseEntity<>(notesViewModel,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody NoteModeView modeView){

        var note=mapper.covertNoteToEntity(modeView);
        noteRepository.save(note);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delNote(@PathVariable int id){
        noteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
