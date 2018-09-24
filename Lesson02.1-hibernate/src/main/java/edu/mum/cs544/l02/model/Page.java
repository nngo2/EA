package edu.mum.cs544.l02.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Page {
	@EmbeddedId
	private PagePk pageKey;
	
	private String body;
	
	@OneToMany(mappedBy="noteKey.pageKey")
	private List<PageNote> pageNotes = new ArrayList<PageNote>();

	public PagePk getPageKey() {
		return pageKey;
	}

	public void setPageKey(PagePk pageKey) {
		this.pageKey = pageKey;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public List<PageNote> getPageNotes() {
		return pageNotes;
	}

	public void setPageNotes(List<PageNote> pageNotes) {
		this.pageNotes = pageNotes;
	}

}
