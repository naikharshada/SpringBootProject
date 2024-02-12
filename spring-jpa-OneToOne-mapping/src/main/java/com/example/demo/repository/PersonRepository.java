package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	@Modifying
	@Query(value = "delete from Person where person_id = ?1", nativeQuery = true)
	@Transactional
	public Integer deletePersonNativeQ(int person_id);
	
	@Modifying
	@Query(value = "update Person set name = ?1, location = ?2, phone = ?3 where person_id = ?4", nativeQuery = true)
	@Transactional
	public Integer updatePersonNativeQ(String name, String location, long phone, int person_id);
	
}
