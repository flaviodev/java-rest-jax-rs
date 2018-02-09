package br.com.flaviodev.rest.model;

public class Question {

	private String id;
	private String statement;
	private String answer;

	public Question() {

	}

	public Question(String id, String statement, String answer) {
		this.id = id;
		this.statement = statement;
		this.answer = answer;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
