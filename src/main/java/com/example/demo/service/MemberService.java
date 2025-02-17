package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Member;

public interface MemberService {
	// create
	void addMember(Member member) throws ServiceException;
	
	// read
	List<Member> findAllMember() throws ServiceException;
	List<Member> findMemberByAddress(String address) throws ServiceException;
	Member login(String username,String password) throws ServiceException;
	boolean checkUsernameBeenUsed(String username) throws ServiceException;
	
	// update
	void updateMember(int id, String name, String username, String password, String address) throws ServiceException;
	
	// delete
	void deleteMemberById(int id) throws ServiceException;
	
}
