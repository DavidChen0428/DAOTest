package com.example.demo.dao.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Member;

@Mapper
public interface MemberMapper {
	// create
	void addMember(Member member);
	
	// read
	List<Member> findAll();
	List<Member> findById(Integer id);
	List<Member> findByUsername(String username);
	List<Member> findByName(String name);
	List<Member> findByAddress(String address);
	
	// update
	void updateMember(Member member);
	
	// delete
	void deleteById(Integer id);
	
}
