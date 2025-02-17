package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DAOException;
import com.example.demo.dao.impl.MemberDaoImpl;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.ServiceException;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDaoImpl memberDaoImpl;

	@Override
	public void addMember(Member member) throws ServiceException {
		if (member != null && member.getUsername() != null) {
			try {
				memberDaoImpl.create(member);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("addMember():Member or Member Username cannot be null.");
		}
	}

	@Override
	public List<Member> findAllMember() throws ServiceException {
		try {
			return memberDaoImpl.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Member> findMemberByAddress(String address) throws ServiceException {
		if (address != null && !address.isEmpty()) {
			try {
				return memberDaoImpl.findSome(address);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("findMemberByAddress():address cannot be null.");
		}
	}

	@Override
	public Member login(String username, String password) throws ServiceException {
		if (username != null && !username.isEmpty()) {
			Member member;
			try {
				member = memberDaoImpl.findOne(username);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			if (member.getPassword().equals(password)) {
				return member;
			} else {
				throw new ServiceException("login():username/password error.");
			}
		} else {
			throw new ServiceException("login():username cannot be null.");
		}
	}

	@Override
	public boolean checkUsernameBeenUsed(String username) throws ServiceException {
		boolean isUsernameBeenUsed = false;
		try {
			if (memberDaoImpl.findOne(username) == null) {
				isUsernameBeenUsed = false;
			} else {
				isUsernameBeenUsed = true;
			}
			return isUsernameBeenUsed;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateMember(int id, String name, String username, String password, String address)
			throws ServiceException {
		Member member;
		try {
			member = memberDaoImpl.findOne(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (member == null) {
			throw new ServiceException("updateMember():Member doesn't exist." + id);
		}
		member.setName(name);
		member.setUsername(username);
		member.setPassword(password);
		member.setAddress(address);
		try {
			memberDaoImpl.update(member);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteMemberById(int id) throws ServiceException {
		try {
			memberDaoImpl.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
