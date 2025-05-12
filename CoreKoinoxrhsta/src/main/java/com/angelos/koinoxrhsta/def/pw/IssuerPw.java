package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;

@Deprecated
@CrossOrigin
@Repository
public interface IssuerPw extends JpaRepository<Issuer, IssuerKey>{

}
