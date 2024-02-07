package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//JPQAL query
	@Query("select s from Student s")
	public List<Student> getAllStudentUsingJPAQL();
	
	@Query("select s from Student s where s.Address = :a order by s.rollNo desc")
	public List<Student> getStudentByAddressUsingJPAQL(@Param("a") String Address);
	
	@Query("select s from Student s where s.Address = :a or s.name = :n")
	public List<Student> getStudentByNameOrAddressUsingJPAQL(@Param("a") String Address, @Param("n") String name);
	
	@Modifying
	@Query("Update Student set name = :name where rollNo = :rollNo")
	@Transactional
	public Integer updateNameByRollnoJPAQL(int rollNo, String name);
	
	
	//NativeQuery
	@Query(value = "select * from Student", nativeQuery = true)
	public List<Student> getAllStudentUsingNativeQuery();
	
	@Query(value = "select * from Student where Address = :a", nativeQuery = true)
	public List<Student> getStudentByAddressUsingNative(@Param("a") String Address);
	
	@Modifying
	@Query(value = "insert into Student(roll_no, name, address)values(?1, ?2, ?3)", nativeQuery = true)
	@Transactional
	public Integer insertStudentNative(int rollNo, String name, String Address);
	
	@Modifying
	@Query(value = "update Student set name = ?1, address = ?2 where roll_no = ?3", nativeQuery = true)
	@Transactional
	public Integer updateStudentNative(String name, String Address, int rollNo);
	
	@Modifying
	@Query(value = "Update Student set name = :name where roll_no = :rollNo", nativeQuery = true)
	@Transactional
	public Integer updateNameByRollnoNative(String name, int rollNo);
	
	@Modifying
	@Query(value = "delete from Student where roll_no = ?1", nativeQuery = true)
	@Transactional
	public Integer deleteStudentNative(int rollNo);
}