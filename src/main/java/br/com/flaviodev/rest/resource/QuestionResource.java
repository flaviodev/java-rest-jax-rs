package br.com.flaviodev.rest.resource;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.flaviodev.rest.model.Option;
import br.com.flaviodev.rest.model.Question;
import br.com.flaviodev.rest.repository.QuestionRepository;
import br.com.flaviodev.rest.repository.QuestionRepositoryImpl;

@Path("questions")
public class QuestionResource {

	private QuestionRepository repository = new QuestionRepositoryImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Question> getQuestions() {
		return repository.getAll();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Question getQuestion(@PathParam("id") String id) {
		return repository.get(id);
	}

	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Question getQuestionQuery(@QueryParam("id") String id) {
		return repository.get(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postQuestion(Question question) {
		question = repository.save(question);
		URI uri = URI.create("/questions/" + question.getId());
		return Response.created(uri).build();
	}

	@Path("{id}/options/{optionCode}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Option getOption(@PathParam("id") String id, @PathParam("optionCode") String optionCode) {
		return repository.getOption(id, optionCode);
	}

	@Path("{id}/options/{optionCode}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeOption(@PathParam("id") String id, @PathParam("optionCode") String optionCode) {
		boolean removed = repository.removeOption(id, optionCode);
		return removed ? Response.ok().build() : Response.noContent().build();
	}

	@Path("{id}/options")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOption(@PathParam("id") String id, Option option) {
		option = repository.addOption(id, option);
		URI uri = URI.create("/questions/" + id + "/options/" + option.getOptionCode());
		return Response.created(uri).build();
	}

	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putQuestion(@PathParam("id") String id, Question question) {
		question.setId(id);
		question = repository.save(question);

		return Response.accepted().build();
	}

	@Path("{id}")
	@DELETE
	public Response deleteQuestion(@PathParam("id") String id) {
		return repository.delete(id) ? Response.ok().build() : Response.noContent().build();
	}
}
