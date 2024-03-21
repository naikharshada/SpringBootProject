package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "firstProcedure", procedureName = "getTickets"),
	@NamedStoredProcedureQuery(name = "secondProcedure", procedureName = "getTicketsByCategory", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "tcategory", type = String.class) }) })
public class Ticket {	
	@Id
	private int id;
	private int amount;
	private String category;
}