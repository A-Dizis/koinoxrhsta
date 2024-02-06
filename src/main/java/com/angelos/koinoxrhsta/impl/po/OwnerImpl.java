package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.def.po.Owner;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@IdClass(OwnerKey.class)
@Table(name = "TBOWNER")
public class OwnerImpl implements Owner, Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_generator")
	@SequenceGenerator(name = "owner_generator", sequenceName = "SEQ_OWNER_ID", allocationSize = 1)
	@Column(name = "OWNER_ID", nullable = false)
	Long ownerId;
	
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
    private LocalDate birthDate;

    /**
	 * sex
	 */
    @Column(name = "SEX")
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;
    
    /**
	 *
	 */
    @Transient
    @Setter(value = AccessLevel.NONE) @Getter(value = AccessLevel.NONE) @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "")
    private List<Flat> flats;
}