package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ticket;

import jakarta.persistence.EntityManager;

@Repository
public class TicketRepository {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketInfo() {
		return em.createNamedStoredProcedureQuery("firstProcedure").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketInfoByCategory(String input) {
		return em.createNamedStoredProcedureQuery("secondProcedure").setParameter("tcategory", input).getResultList();
	}
}