package com.example.domain;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data								// @Entity + @Id 는 필수 사용할 것.
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

	@Id								// 필수로 하나 이상의 PRIMARY KEY가 필요하다.
	@Column(precision=2)
	private BigInteger deptno;  
	@Column(length=14, nullable=false)
	private String dname;
	@Column(length=13)
	private String loc;
}
