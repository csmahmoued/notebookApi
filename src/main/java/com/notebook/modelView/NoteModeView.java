package com.notebook.modelView;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NoteModeView {

    private String noteId;
    private String title;
    private String text;
    private String notebookId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yy hh:mm:ss")
    private Date lastModeification;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }

    public Date getLastModeification() {
        return lastModeification;
    }

    public void setLastModeification(Date lastModeification) {
        this.lastModeification = lastModeification;
    }
}
