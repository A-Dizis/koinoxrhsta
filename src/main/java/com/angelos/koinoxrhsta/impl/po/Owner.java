package com.angelos.koinoxrhsta.impl.po;

import java.sql.Date;
import java.util.List;

import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@IdClass(OwnerKey.class)
@Table(name = "TBOWNER")
public class Owner {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_generator")
	@SequenceGenerator(name = "owner_generator", sequenceName = "SEQ_OWNER_ID", allocationSize = 1)
	@Column(name = "OWNER_ID", nullable = false)
	Long ownerId;
	
	/**
	 * buildingId
	 */
    @Column(name = "BUILDING_ID")
	private Long buildingId;
	
	/**
	 * flatId
	 */
    @Column(name = "FLAT_ID")
	private Long flatId;

	/**
	 * name
	 */
    @Column(name = "NAME", length = 25)
    private String name;

    /**
	 * surname
	 */
    @Column(name = "SURNAME", length = 25)
    private String surname;

    /**
	 * birthDate
	 */
    @Column(name = "BIRTH_DT")
    private Date birthDate;

    /**
	 * sex
	 */
    @Column(name = "SEX")
    private Short sex;
    
    /**
	 *
	 */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns(
    	    {
    	    	@JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false),
    	    	@JoinColumn(name = "FLAT_ID", referencedColumnName = "FLAT_ID", nullable = false)
    	    })
    private List<Flat> flat;
}