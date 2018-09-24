package edu.mum.cs544.l02.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PageNotePk implements Serializable{

	private static final long serialVersionUID = -4873866616021867750L;

	private PagePk pageKey;
	private int noteId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noteId;
		result = prime * result + ((pageKey == null) ? 0 : pageKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageNotePk other = (PageNotePk) obj;
		if (noteId != other.noteId)
			return false;
		if (pageKey == null) {
			if (other.pageKey != null)
				return false;
		} else if (!pageKey.equals(other.pageKey))
			return false;
		return true;
	}
	
	public PagePk getPageKey() {
		return pageKey;
	}
	public void setPageKey(PagePk pageKey) {
		this.pageKey = pageKey;
	}	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
}
