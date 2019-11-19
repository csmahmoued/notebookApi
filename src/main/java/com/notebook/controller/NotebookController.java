package com.notebook.controller;

import com.notebook.mapper.Mapper;
import com.notebook.model.Notebook;
import com.notebook.modelView.NotebookModelView;
import com.notebook.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notebook")
@CrossOrigin
public class NotebookController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private NotebookRepository notebookRepository;

    @GetMapping("/all")
    public ResponseEntity<List<NotebookModelView>> getALL(){
        var model=notebookRepository.findAll();
        var notebooks=model.stream().map(n->mapper.covertNoteBookModeView(n)).collect(Collectors.toList());
        return new ResponseEntity<>(notebooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotebookModelView> getById(@PathVariable int id){
        var model=notebookRepository.findById(id).get();
        var notebook=mapper.covertNoteBookModeView(model);
        return new ResponseEntity<>(notebook,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Notebook> addNotebook(@RequestBody NotebookModelView modelView){
        var model=mapper.covertToNoteBookEntity(modelView);
        var notebook=notebookRepository.save(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> del(@PathVariable int id){
        notebookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }











}
