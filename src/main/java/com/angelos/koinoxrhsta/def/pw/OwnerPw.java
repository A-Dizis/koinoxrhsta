package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.def.po.Owner;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;

@Repository
public interface OwnerPw extends JpaRepository<Owner, OwnerKey>{

}
