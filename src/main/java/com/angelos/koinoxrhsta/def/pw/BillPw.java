package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.def.po.Bill;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;

@Repository
public interface BillPw extends JpaRepository<Bill, BillKey>{

}
