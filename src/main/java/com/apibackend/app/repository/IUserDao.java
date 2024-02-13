package com.apibackend.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.apibackend.app.entity.User;



public interface IUserDao extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	/*@Query("select u from Usuario u where u.username=?1")
	public User findByUsername2(String username);*/
}
