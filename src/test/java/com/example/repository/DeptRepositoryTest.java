package com.example.repository;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Dept;

@RunWith(SpringRunner.class)
@DataJpaTest												// 가상 DB Test
public class DeptRepositoryTest {

	@Inject
	DeptRepository deptRepository;
	
	Dept d10 = new Dept(new BigInteger("10"),"경리부","서울");
	Dept d20 = new Dept(new BigInteger("20"),"인사부","인천");
	Dept d30 = new Dept(new BigInteger("30"),"영업부","용인");
	Dept d40 = new Dept(new BigInteger("40"),"전산부","수원");
	
	List<Dept> list = Arrays.asList(d10,d20,d30,d40);
			

	@Before
	public void before(){
		deptRepository.save(list);
	}
	

	
	@Test
	public void findAll() {
		System.out.println("deptRepository = "+deptRepository.getClass());
		deptRepository.findAll().forEach(e -> {
			System.out.println(e);
		});
		
		assertThat(deptRepository.findAll(), is(list));
		assertThat(deptRepository.findAll()).hasSize(4).isEqualTo(list);
	}
	
	@Test
	public void count(){
		long cnt = deptRepository.count();
		
		assertThat(cnt, is(4L));
		assertThat(cnt).isEqualTo(4L).isLessThan(10L).isGreaterThan(1L);
	}
	
	@Test
	public void save(){
		Dept dept = new Dept(new BigInteger("10"),"xxx","yyy");
		
		System.out.println(deptRepository.findOne(new BigInteger("10")));
		
		deptRepository.save(dept);
		System.out.println(deptRepository.findOne(new BigInteger("10")));
		
		assertThat(deptRepository.save(dept),is(dept));
		assertThat(deptRepository.findOne(new BigInteger("10")), is(dept));
		assertThat(deptRepository.findOne(new BigInteger("10")))
									 .isEqualTo(dept)
									 .hasFieldOrPropertyWithValue("deptno", new BigInteger("10"))
									 .hasFieldOrPropertyWithValue("dname", "xxx")
									 .hasFieldOrPropertyWithValue("loc", "yyy");
	}
	
	@Test
	public void delete(){
		deptRepository.delete(new BigInteger("20"));
		
		assertThat(deptRepository.findOne(new BigInteger("20")),is(nullValue()));
		assertThat(deptRepository.findOne(new BigInteger("20"))).isNull();
	}
	
	@Test
	public void deleteAll(){
		deptRepository.deleteAll();
		
		assertThat(deptRepository.findAll()).hasSize(0).isNotNull().isEmpty();
	}
	
	@Test
	public void findByLoc(){
		Dept dept = deptRepository.findByLoc("서울");
		System.out.println(dept);
		
		assertThat(deptRepository.findByLoc("서울")).hasFieldOrPropertyWithValue("dname","경리부");
	}
	
	@Test
	public void findByDnameOrLoc(){
		deptRepository.findByDnameOrLoc("경리부", "용인").forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	public void findByDeptnoGreaterThanOrderByDnameDesc(){
		deptRepository.findByDeptnoGreaterThanOrderByDnameDesc(new BigInteger("20")).forEach(e -> {
			System.out.println(e);
		});
	}
	
	@Test
	public void findByDeptnoBetween(){
		deptRepository.findByDeptnoBetween(new BigInteger("20"), (new BigInteger("30"))).forEach(e -> {
			System.out.println(e);
		});
	}
	
}
