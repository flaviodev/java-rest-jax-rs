package br.com.flaviodev.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
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
		Assert.assertEquals(question.getId(), "1");
	}
	
	@Test
	public void serviceAlterQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = new Question("1", "Question A", "AA");
		Response response = target.path("/questions/1").request().put(Entity.entity(question, MediaType.APPLICATION_JSON));
		Assert.assertEquals(response.getStatus(),202);
	}

	@Test
	public void serviceIncludeQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Question question = new Question(null, "Question H", "AH");
		Response response = target.path("/questions").request().post(Entity.entity(question, MediaType.APPLICATION_JSON));
		Assert.assertEquals(response.getStatus(),201);
	}

	@Test
	public void serviceDeleteQuestion() {
		WebTarget target = client.target("http://localhost:8080");
		Response response = target.path("/questions/4").request().delete();
		Assert.assertEquals(response.getStatus(), 200);
	}
}
