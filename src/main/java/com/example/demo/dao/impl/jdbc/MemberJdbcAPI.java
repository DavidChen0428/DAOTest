package com.example.demo.dao.impl.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Member;

@Component
// 類似Repository或是Mapper
public class MemberJdbcAPI {
	private JdbcTemplate jdbcTemplate;
	
	public MemberJdbcAPI(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	// create
	void addMember(Member member) {
		String SQL="INSERT INTO member(name, username, password, address) VALUES(?,?,?,?)";
		jdbcTemplate.update(SQL, member.getName(),member.getUsername(),member.getPassword(),member.getAddress());
	}
	
	// read
	List<Member> findAll(){
		String SQL="SELECT * FROM member";
		return jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Member.class));
	}
	
	List<Member> findByAddress(String address){
		String SQL="SELECT * FROM member WHERE address=?";
		return jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Member.class),address);
	}
	
	List<Member> findById(int id){
		String SQL="SELECT * FROM member WHERE id=?";
		return jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Member.class),id);
	}
	
	List<Member> findByUsername(String username){
		String SQL="SELECT * FROM member WHERE username=?";
		return jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Member.class),username);
	}
	
	List<Member> findByName(String name){
		String SQL="SELECT * FROM member WHERE name=?";
		return jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Member.class),name);
	}
	
	// update
	void updateMember(Member member) {
		String SQL="UPDATE member SET name=?, username=?, password=?, address=? WHERE id=?";
		jdbcTemplate.update(SQL,member.getName(),member.getUsername(),member.getPassword(),member.getAddress(),member.getId());
	}
	
	// delete
	public void deleteById(int id) {
		String SQL="DELETE FROM member WHERE id=?";
		jdbcTemplate.update(SQL,id);
	}
	
}
