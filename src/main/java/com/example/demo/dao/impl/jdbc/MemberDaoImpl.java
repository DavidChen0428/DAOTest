package com.example.demo.dao.impl.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.DAO;
import com.example.demo.dao.DAOException;
import com.example.demo.model.Member;

@Repository
public class MemberDaoImpl implements DAO<Member>{
	@Autowired
	MemberJdbcAPI memberJdbcAPI;
	
	@Override
	public void create(Member data) throws DAOException {
		memberJdbcAPI.addMember(data);
	}

	@Override
	public List<Member> findAll() throws DAOException{
		return memberJdbcAPI.findAll();
	}
	
	// 找出部分資料
	// 找地址
	@Override
	public List<Member> findSome(Object key) throws DAOException{
		if(key instanceof String) {
			if(existsByAddress((String)key)) {
				List<Member> list=memberJdbcAPI.findByAddress((String)key);
				if(list.size()==0) {
					throw new DAOException("findSome():No matching data found.");
				}
				return list;
			}else {
				throw new DAOException("findSome():Currently only applies to address.");
			}
		}else {
			throw new DAOException("findSome():Unsupported key type.");
		}
	}
	
	boolean existsByAddress(String key) {
		if(memberJdbcAPI.findByAddress(key).size()!=0) {
			return true;
		}else {
			return false;
		}
	}

	// 找出單一一筆資料
	// 找id, 姓名, 帳戶
	@Override
	public Member findOne(Object key) throws DAOException {
		if (key instanceof Integer) {
			int keyValue=(Integer) key;
			return memberJdbcAPI.findById(keyValue).get(0);
		} else if (key instanceof String) {
			Member member=memberJdbcAPI.findByUsername((String)key).get(0);
			if(member==null) {
				member=memberJdbcAPI.findByName((String)key).get(0);
				if(member==null) {
					throw new DAOException("findOne():No matching data found.");
				}
			}
			return member;
		}else {
			throw new DAOException("findOne():Unsupported key type.");
		}
	}

	@Override
	public void update(Member data) throws DAOException {
		memberJdbcAPI.updateMember(data);
	}

	@Override
	public void delete(Object key) throws DAOException {
		if(key instanceof Integer) {
			memberJdbcAPI.deleteById((Integer) key);
		}else {
			throw new DAOException("delete():currently only applies to Id");
		}
	}
}
