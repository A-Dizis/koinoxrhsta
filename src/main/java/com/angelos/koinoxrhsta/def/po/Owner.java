package com.angelos.koinoxrhsta.def.po;

import java.time.LocalDate;

import com.angelos.koinoxrhsta.impl.enums.Sex;

public interface Owner {

	Long getOwnerId();

	void setOwnerId(Long ownerId);

	String getName();

	void setName(String name);

	String getSurname();

	void setSurname(String surname);

	LocalDate getBirthDate();

	void setBirthDate(LocalDate birthDate);

	Sex getSex();

	void setSex(Sex sex);

}