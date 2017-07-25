package com.zhao.spring_boot_sign.sign;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SignRepository extends JpaRepository<SignDemo,String>{
	List<SignDemo> findAllByUsernameLike(String username);
	List<SignDemo> findAllByDateBetween(Date startDate, Date endDate);

}
