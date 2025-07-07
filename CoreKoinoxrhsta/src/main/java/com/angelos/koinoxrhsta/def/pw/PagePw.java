package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.Page;
import com.angelos.koinoxrhsta.impl.po.keys.PageKey;

@Deprecated
@CrossOrigin
@Repository
public interface PagePw extends JpaRepository<Page, PageKey>{

}