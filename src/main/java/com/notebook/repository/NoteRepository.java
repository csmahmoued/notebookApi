package com.notebook.repository;

import com.notebook.model.Note;
import com.notebook.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {

    List<Note> findAllByNotebook(Notebook notebook);
}
