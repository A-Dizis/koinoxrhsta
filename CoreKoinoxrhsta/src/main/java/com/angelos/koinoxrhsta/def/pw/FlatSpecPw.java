package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;

@Deprecated
@CrossOrigin
@Repository
public interface FlatSpecPw extends JpaRepository<FlatSpec, FlatSpecKey>{

}
