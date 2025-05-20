package com.theanh1301.myapp.repository;

import com.theanh1301.myapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    //extends JPA đỡ phải viết persist , merge , remove

}
