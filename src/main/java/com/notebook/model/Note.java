package com.notebook.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int noteId;
    private  String titel;
    private String text;
    private Date lastmodification;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notebook notebook;

    public Note(){
        this.lastmodification=new Date();
    }

    public int getId() {
        return noteId;
    }

    public void setId(int id) {
        this.noteId = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastmodification() {
        return lastmodification;
    }

    public void setLastmodification(Date lastmodification) {
        this.lastmodification = lastmodification;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }
}
