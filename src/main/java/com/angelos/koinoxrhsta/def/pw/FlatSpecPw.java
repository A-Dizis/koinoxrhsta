package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.def.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;

@Repository
public interface FlatSpecPw extends JpaRepository<FlatSpec, FlatSpecKey>{

}
