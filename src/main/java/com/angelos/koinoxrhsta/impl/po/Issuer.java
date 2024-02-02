package com.angelos.koinoxrhsta.impl.po;

import java.util.List;

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
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@IdClass(IssuerKey.class)
@Table(name = "TBISSUER")
public class Issuer {
	
	/**
	 * issuerId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issuer_generator")
    @SequenceGenerator(name="issuer_generator", sequenceName = "SEQ_ISSUER_ID", allocationSize = 1)
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
    @Setter(value = AccessLevel.NONE) @Getter(value = AccessLevel.NONE) @EqualsAndHashCode.Exclude @ToString.Exclude
    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    private List<Bill> bill;
}