package edu.mum.cs544.imdb.domain;

public class SearchCriteria {
	private String criteria;
	private String criteriaType;
	
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getCriteriaType() {
		return criteriaType;
	}
	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}
}
