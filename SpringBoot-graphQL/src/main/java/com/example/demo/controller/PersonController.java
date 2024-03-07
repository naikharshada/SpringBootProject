package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Value("classpath:person.graphqls")
	private Resource schemaResource;
	
	private GraphQL graphQL;
	
	@PostConstruct   //for pre-processing
	public void loadSchema() throws IOException {
		//get the file
		File schemaFile = schemaResource.getFile(); 
		//pass the schema
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		//set data fetcher
		RuntimeWiring wiring = buildWiring();
		//RuntimeWiring to graphql schema
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		//pass the schema in graphql
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildWiring() {
		//create data fetcher
		DataFetcher<List<Person>> fetcher1 = data -> {
			return (List<Person>) personRepository.findAll();
		};
		
		DataFetcher<Person> fetcher2 = data -> {
			return personRepository.findByEmail(data.getArgument("email"));
		};
		
		//set datafetcher in typescript call
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting->
			typeWriting.dataFetcher("getAllPerson", fetcher1).dataFetcher("findPerson", fetcher2)).build();
	}

	@PostMapping("/addPerson")
	public String addPerson(@RequestBody List<Person> persons) {
		personRepository.saveAll(persons);
		return "record inserted " + persons.size();
	}
	
	@GetMapping("/findAllPerson")
	public List<Person> getPersons() {
		return (List<Person>) personRepository.findAll();
	}
	
	@PostMapping("/getAll") 
	public ResponseEntity<Object> getAll(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getPersonByEmail") 
	public ResponseEntity<Object> getByEmail(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
}
