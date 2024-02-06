package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.angelos.koinoxrhsta.def.po.Bill;
import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.def.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(BillKey.class)
@Table(name = "TBBILL")
public class BillImpl implements Bill, Serializable {

	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_generator")
    @SequenceGenerator(name="receipt_generator", sequenceName = "SEQ_RECEIPT_ID", allocationSize = 1)
    @Column(name = "RECEIPT_ID", nullable = false)
	private Long receiptNo;

	/**
	 * 
	 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false)
    private Building building;

    /**
	 * issuer
	 */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ISSUER_ID", referencedColumnName = "ISSUER_ID", insertable = false, updatable = false)
    private Issuer issuer;
	
	/**
	 * 
	 */
    @Column(name = "FLAT_ID")
    private Long flatId;

	/**
	 * 
	 */
    @Column(name = "START_DT")
    private LocalDate startDate;

	/**
	 * 
	 */
    @Column(name = "END_DT")
    private LocalDate endDate;

	/**
	 * 
	 */
    @Column(name = "DUE_DT")
    private LocalDate dueDate;

	/**
	 * 
	 */
    @Column(name = "REF_RECEIPT_ID")
    private Long refReceiptId;

	/**
	 * 
	 */
    @Column(name = "AMOUNT_CHARGED", precision = 10, scale = 2)
    private BigDecimal amountCharged;

	/**
	 * 
	 */
    @Column(name = "AMOUNT_PAID", precision = 10, scale = 2)
    private BigDecimal amountPaid;

	/**
	 * 
	 */
    @Column(name = "IS_PAID")
    private boolean isPaid;

	/**
	 * 
	 */
    @Column(name = "BUILT_DT")
    private LocalDate builtDate;
    
    /**
     * 
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
    	@JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false),
    	@JoinColumn(name = "FLAT_ID", referencedColumnName = "FLAT_ID", nullable = false, insertable = false, updatable = false)
    })
    private Flat flat;
    
}
