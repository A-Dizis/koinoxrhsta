package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.enums.RoleGroup;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.UserKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(UserKey.class)
@Table(name = "TBUSER", schema = "koinoxrhsta")
public class User extends KeyImpl<UserKey> implements Key<UserKey> {
    
    /**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "SEQ_USER_ID", allocationSize = 1, schema = "koinoxrhsta")
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * username
	 */
	@Column(name = "USERNAME")
	private String username;

	/**
	 * password
	 */
	@Column(name = "PASSWORD")
	private String password;

    /**
	 * permissionGroup
	 */
	@Column(name = "PERMISSION_GROUP")
	@Enumerated(EnumType.ORDINAL)
	private RoleGroup permissionGroup;
    
    /**
	 * isActive
	 */
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

    /**
	 * 
	 */
	@Version
	@Column(name = "LAST_VERSION")
	private Long lastVersion;

	/**
	 * @return
	 */
	public UserKey getKey() {
		return super.getKey(UserKey.class);
	}

	/**
	 * 
	 */
	public void setKey(UserKey key) {
		super.setKey(key);
	}

}
