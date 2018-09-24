package edu.mum.cs544.l02.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PageNote {
	@Id
	private PageNotePk noteKey;
	
	private String note;

	public PageNotePk getNoteKey() {
		return noteKey;
	}

	public void setNoteKey(PageNotePk noteKey) {
		this.noteKey = noteKey;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
