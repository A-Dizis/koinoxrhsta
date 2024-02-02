package com.angelos.koinoxrhsta.impl.po;

import java.math.BigDecimal;
import java.sql.Date;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@IdClass(BillKey.class)
@Table(name = "TBBILL")
public class Bill {

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
	@Column(name = "BUILDING_ID")
    private Long buildingId;

	/**
	 * 
	 */
    @Column(name = "FLAT_ID")
    private Long flatId;

	/**
	 * 
	 */
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    @Column(name = "ISSUER_ID")
    private Long issuerId;

	/**
	 * 
	 */
    @Column(name = "START_DT")
    private Date startDate;

	/**
	 * 
	 */
    @Column(name = "END_DT")
    private Date endDate;

	/**
	 * 
	 */
    @Column(name = "DUE_DT")
    private Date dueDate;

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
    private Date builtDate;
    
    /**
	 * issuer
	 */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   	@JoinColumn(name = "ISSUER_ID", nullable = false, insertable = false, updatable = false)
    private Issuer issuer;
}
