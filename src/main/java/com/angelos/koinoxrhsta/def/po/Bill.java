package com.angelos.koinoxrhsta.def.po;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.angelos.koinoxrhsta.def.po.commons.BuildingDependent;
import com.angelos.koinoxrhsta.def.po.commons.FlatDependent;
import com.angelos.koinoxrhsta.def.po.commons.FlatIdDependent;

public interface Bill extends BuildingDependent, FlatIdDependent, FlatDependent {

	Long getReceiptNo();

	void setReceiptNo(Long receiptNo);

	Issuer getIssuer();

	void setIssuer(Issuer issuer);

	LocalDate getStartDate();

	void setStartDate(LocalDate startDate);

	LocalDate getEndDate();

	void setEndDate(LocalDate endDate);

	LocalDate getDueDate();

	void setDueDate(LocalDate dueDate);

	Long getRefReceiptId();

	void setRefReceiptId(Long refReceiptId);

	BigDecimal getAmountCharged();

	void setAmountCharged(BigDecimal amountCharged);

	BigDecimal getAmountPaid();

	void setAmountPaid(BigDecimal amountPaid);

	boolean isPaid();

	void setPaid(boolean isPaid);

	LocalDate getBuiltDate();

	void setBuiltDate(LocalDate builtDate);

}
