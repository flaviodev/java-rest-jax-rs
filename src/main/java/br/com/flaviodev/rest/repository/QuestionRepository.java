package br.com.flaviodev.rest.repository;

import br.com.flaviodev.rest.model.Option;
import br.com.flaviodev.rest.model.Question;

public interface QuestionRepository extends CRUDBaseRepository<String, Question> {

	Option addOption(String id, Option option);

	boolean removeOption(String id, String optionCode);

	Option getOption(String id, String optionCode);

}
