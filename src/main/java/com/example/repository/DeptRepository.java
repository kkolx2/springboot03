package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Dept;

public interface DeptRepository extends JpaRepository<Dept, BigInteger>{
	Dept findByLoc(String loc);																	// Query Method
	
	@Query("select d from Dept d where d.dname = ?1 or d.loc = ?2")
	List<Dept> selectDept(String dname,String loc);
	
	
	@Query("select d from Dept d where d.dname = :dname or d.loc = :loc")					// JPQL (JPA SQL)
	List<Dept> selectDept2(@Param("dname") String dname, @Param("loc") String loc);
	
	@Query("select d.dname, d.loc from Dept d order by d.loc")
	List<Object[]> selectDnameLoc();
	
	@Query("select count(d.dname) from Dept d")
	List<Object[]> myCount();
	
	List<Dept> findByDnameOrLoc(String dname, String loc);
	List<Dept> findByDeptnoGreaterThanOrderByDnameDesc(BigInteger gt);
	List<Dept> findByDeptnoBetween(BigInteger left, BigInteger right);
	
}