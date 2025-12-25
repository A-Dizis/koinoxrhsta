package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.User;
import com.angelos.koinoxrhsta.impl.po.keys.UserKey;


@Deprecated
@CrossOrigin
@Repository
public interface UserPw extends JpaRepository<User, UserKey>{

}
