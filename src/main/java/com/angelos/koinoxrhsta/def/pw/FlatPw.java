package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@Repository
public interface FlatPw extends JpaRepository<Flat, FlatKey>{

}
