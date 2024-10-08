package com.angelos.koinoxrhsta.impl.po;

import java.util.List;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(IssuerKey.class)
@Table(name = "TBISSUER")
public class Issuer extends KeyImpl<IssuerKey> implements Key<IssuerKey> {

    /**
     * issuerId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issuer_generator")
    @SequenceGenerator(name = "issuer_generator", sequenceName = "SEQ_ISSUER_ID", allocationSize = 1)
    @Column(name = "ISSUER_ID", nullable = false)
    private Long issuerId;

    /**
     * name
     */
    @Column(name = "NAME", length = 36)
    private String name;

    /**
     * serviceDescription
     */
    @Column(name = "SERVICE_DESC", length = 150)
    private String serviceDescription;

    /**
     * 
     */
    @Version
    @Column(name = "LAST_VERSION")
    private Long lastVersion;

    /**
     *
     */
    @Transient
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    private List<Bill> bills;

    /**
     * @return
     */
    public IssuerKey getKey() {
        return super.getKey(IssuerKey.class);
    }

    /**
     * 
     */
    @Override
    public void setKey(IssuerKey key) {
        super.setKey(key);
    }

}