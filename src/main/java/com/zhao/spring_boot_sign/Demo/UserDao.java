package com.zhao.spring_boot_sign.Demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<Account,Long>{

    public Account findByUsernameAndUserpaw(String username,String userpaw);


}
