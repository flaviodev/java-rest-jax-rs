package br.com.flaviodev.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.flaviodev.rest.model.Question;

public class QuestionsTest  {
	
	private static HttpServer server;
	private static Client client;

	@BeforeClass
	public static void before(){
		server = Server.startServer();
		ClientConfig config = new ClientConfig();
		client = ClientBuilder.newClient(config);
	}		
	
	@AfterClass
	public static void after(){
		Server.stopServer(server);
	}

	@Test
	public void serviceReturnQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = target.path("/questions/1").request().get(Question.class);
		Assert.assertEquals("1",question.getId());
	}

	@Test
	public void serviceReturnQuestionByQuery() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = target.path("/questions/get").queryParam("id", "1").request().get(Question.class);
		Assert.assertEquals( "1", question.getId());
	}
	
	@Test
	public void serviceAlterQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = new Question("1", "Question A", "B");
		Response response = target.path("/questions/1").request().put(Entity.entity(question, MediaType.APPLICATION_JSON));
		Assert.assertEquals(202,response.getStatus());
	}

	@Test
	public void serviceIncludeQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = new Question(null, "Question H", "A");
		Response response = target.path("/questions").request().post(Entity.entity(question, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("location");
		Question questionReturned = client.target(location).request().get(Question.class);
		Assert.assertEquals("A", questionReturned.getCorrectOptionCode());
	}

	@Test
	public void serviceDeleteQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Response response = target.path("/questions/4").request().delete();
		Assert.assertEquals(200, response.getStatus());
	}
}
