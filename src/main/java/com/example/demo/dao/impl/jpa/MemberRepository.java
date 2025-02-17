package com.example.demo.dao.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Member;

public interface MemberRepository extends JpaRepository<Member,Integer>{
	// findSome()
	boolean existsByAddress(String address);
	List<Member> findByAddress(String address);
	
	// findOne()
	List<Member> findById(int id);
	List<Member> findByUsername(String username);
	List<Member> findByName(String name);
	
	
	boolean existsByUsername(String username);
}
