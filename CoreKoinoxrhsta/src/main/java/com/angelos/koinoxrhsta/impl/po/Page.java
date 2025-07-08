package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.PageKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(PageKey.class)
@Table(name = "TBPAGE", schema = "koinoxrhsta")
public class Page extends KeyImpl<PageKey> implements Key<PageKey> {

    /**
     * page id
     */
    @Id
    @Column(name = "PAGE_ID", nullable = false)
    private Long pageId;

    /**
    * name
    */
    @Column(name = "NAME")
    private String name;

    /**
     * page classpath
     */
    @Column(name = "CLASSPATH")
    private String classpath;

    /**
     * relative url
     */
    @Column(name = "RELATIVE_URL")
    private String relativeUrl;

    /**
     * @return key.
     */
    public PageKey getKey() {
        return super.getKey(PageKey.class);
    }

    /**
     * Set key.
     */
    public void setKey(PageKey key) {
        super.setKey(key);
    }
}