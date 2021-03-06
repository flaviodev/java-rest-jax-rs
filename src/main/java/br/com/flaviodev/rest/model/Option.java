package br.com.flaviodev.rest.model;

public class Option {

	private String id;
	private String optionCode;
	private String statementOption;

	public Option() {

	}

	public Option(String statementOption, String optionCode) {
		this.statementOption = statementOption;
		this.optionCode = optionCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getStatementOption() {
		return statementOption;
	}

	public void setStatementCode(String statementOption) {
		this.statementOption = statementOption;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Option other = (Option) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}