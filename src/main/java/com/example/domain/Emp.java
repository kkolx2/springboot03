package com.example.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
//@ToString(exclude={"dept"})
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

	public enum Gender{
		M,F;
	}
	
	
	@Id										// @Entity + @Id 는 필수 사용할 것.
	@Column(precision=4, length=4)
 	private BigInteger empno;
	
	@Column(length=10, nullable=false)
 	private String ename;
	
	@Enumerated(EnumType.STRING)
	@Column(length=1)
 	private Gender gender;
	
 	@Column(length=9)
 	private String job;
 	
	@Column(precision=4, length=4)
 	private Integer mgr;
	
	@Temporal(TemporalType.DATE)
 	@Column(nullable=false)
 	private Date hiredate;
	
 	@Column(precision=7, scale=2)
 	private BigDecimal sal;
 	
 	@Column(precision=7, scale=2)
 	private BigDecimal comm;
 	
// 	@Column(precision=2, length=2)
// 	private Integer deptno; 
	
 	@ManyToOne(fetch=FetchType.LAZY)					// 필요시에만 조회한다 (성능 향상)
// 	@ManyToOne(fetch=FetchType.EAGER)					// 조회 가능한 모든 데이터를 처음에 전부 가져온다.
 	@JoinColumn(name="deptno")
 	private Dept dept;
 	
}
