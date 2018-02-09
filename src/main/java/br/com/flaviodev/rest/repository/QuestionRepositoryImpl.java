package br.com.flaviodev.rest.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.flaviodev.rest.model.Question;

public class QuestionRepositoryImpl implements QuestionRepository {

	private static Map<String, Question> questions;

	static {
		questions = new HashMap<>();
		questions.put("1", new Question("1", "Question one", "A"));
		questions.put("2", new Question("2", "Question two", "B"));
		questions.put("3", new Question("3", "Question three", "C"));
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
			question.setAnswer(item.getAnswer());
			
			return item;
		} else {
			item.setId(String.valueOf(questions.size() + 1));
			questions.put(item.getId(), item);
			
			return item;
		}
	}

	public boolean delete(String id) {
		return questions.remove(id) != null;
	}

}
