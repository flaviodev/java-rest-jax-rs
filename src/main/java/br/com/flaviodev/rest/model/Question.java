package br.com.flaviodev.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private String id;
	private String statement;
	private List<Option> options;
	private String correctOptionCode;

	public Question() {

	}

	public Question(String id, String statement, String correctOptionCode) {
		this.id = id;
		this.statement = statement;
		this.correctOptionCode = correctOptionCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public String getCorrectOptionCode() {
		return correctOptionCode;
	}

	public void setCorrectOptionCode(String correctOptionCode) {
		this.correctOptionCode = correctOptionCode;
	}

	public void addOption(Option option) {
		if(options==null) 
			options = new ArrayList<>();
		
		options.add(option);
	}
	
	public void removeOption(Option option) {
		if(options==null) 
			options = new ArrayList<>();
		
		options.remove(option);
	}
	
}
