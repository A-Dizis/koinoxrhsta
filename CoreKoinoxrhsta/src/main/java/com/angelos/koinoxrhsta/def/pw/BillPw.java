package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;

@Deprecated
@CrossOrigin
@Repository
public interface BillPw extends JpaRepository<Bill, BillKey>{

}
