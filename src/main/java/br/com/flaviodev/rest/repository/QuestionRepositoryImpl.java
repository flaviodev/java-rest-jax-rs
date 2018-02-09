package br.com.flaviodev.rest.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.flaviodev.rest.model.Option;
import br.com.flaviodev.rest.model.Question;

public class QuestionRepositoryImpl implements QuestionRepository {

	private static Map<String, Question> questions;

	static {
		Question question1 = new Question("1", "Question one", "A");
		question1.addOption(new Option("option 1", "A"));
		question1.addOption(new Option("option 2", "B"));
		question1.addOption(new Option("option 3", "C"));

		Question question2 = new Question("2", "Question two", "B");
		question2.addOption(new Option("option 1", "A"));
		question2.addOption(new Option("option 2", "B"));
		question2.addOption(new Option("option 3", "C"));

		Question question3 = new Question("3", "Question three", "C");
		question3.addOption(new Option("option 1", "A"));
		question3.addOption(new Option("option 2", "B"));
		question3.addOption(new Option("option 3", "C"));

		questions = new HashMap<>();
		questions.put("1", question1);
		questions.put("2", question2);
		questions.put("3", question3);
	}

	public Collection<Question> getAll() {
		return questions.values();
	}

	public Question get(String id) {
		return questions.get(id);
	}

	public Question save(Question item) {
		Question question = questions.get(item.getId());

		if (question != null) {
			question.setStatement(item.getStatement());
			question.setCorrectOptionCode(item.getCorrectOptionCode());

			return item;
		} else {
			item.setId(String.valueOf(questions.size() + 1));
			questions.put(item.getId(), item);

			return item;
		}
	}

	public Option getOption(String id, String optionCode) {
		Question question = questions.get(id);
		Option option = null;

		if (question != null && question.getOptions().size() > 0) {
			option = question.getOptions().stream().filter(o -> o.getOptionCode().equalsIgnoreCase(optionCode))
					.findFirst().get();
		}

		return option;
	}

	public Option addOption(String id, Option option) {
		Question question = questions.get(id);
		option.setId(String.valueOf(question.getOptions().size()));
		question.addOption(option);

		return option;
	}

	public boolean removeOption(String id, String optionCode) {
		Question question = questions.get(id);
		Option option = getOption(id, optionCode);

		if (option != null) {
			question.removeOption(option);
			return true;
		}

		return false;
	}

	public boolean delete(String id) {
		return questions.remove(id) != null;
	}

}
