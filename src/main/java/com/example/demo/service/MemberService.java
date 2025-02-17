package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Member;

public interface MemberService {
	// create
	void addMember(Member member);
	
	// read
	List<Member> findAllMember();
	List<Member> findMemberByAddress(String address);
	Member Login(String username,String password);
	boolean checkUsernameBeenUsed(String username);
	
	// update
	void updateMember(int id, String name, String username, String password, String address);
	
	// delete
	void deleteMemberById(int id);
	
}
