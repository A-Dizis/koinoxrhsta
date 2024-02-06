package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.impl.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;

@Repository
public interface IssuerPw extends JpaRepository<Issuer, IssuerKey>{

}
