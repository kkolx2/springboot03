package com.example.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Emp;
import com.example.domain.Emp.Gender;

public interface EmpRepository extends JpaRepository<Emp, BigInteger>{
	
	@Query("select e from Emp e inner join e.dept d where d.deptno = :deptno")
	List<Emp> selectEmpByDeptno(@Param("deptno")BigInteger deptno);
	
	@Query("select e from Emp e, Dept d where e.dept = d.deptno and d.deptno = :deptno")
	List<Emp> selectEmpByDeptno2(@Param("deptno")BigInteger deptno);
	
	@Query("select avg(e.sal), max(e.sal), min(e.sal) from Emp e")
	List<Object[]> stat();
	
	@Query("select new Map(avg(e.sal) as avg, "
			+ 			  	 "max(e.sal) as max, "
			+ 				 "min(e.sal) as min"
			+ ")"
			+ " from Emp e")
	List<Map<String,Object>> stat2();
	
	@Query("select new Map(e.dept.deptno as deptno, "
			+				 "avg(e.sal) as avg, "
			+ 				 "max(e.sal) as max, "
			+ 				 "min(e.sal) as min"
			+ ")"
			+ " from Emp e "
			+ "group by e.dept")
	List<Map<String,Object>> stat3();
	
	
	List<Emp> findByEmpnoBetween(BigInteger left,BigInteger right);
	List<Emp> findByGenderOrderByEmpno(Gender g);
	List<Emp> findByGenderOrderByEmpnoDesc(Gender g);
	int deleteByGender(Gender g);
	
//	List<Emp> findByHiredate
}
