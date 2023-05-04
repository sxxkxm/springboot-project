package com.example.demo.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.data.entity.common.DefaultEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = UUIDGenerator.class, property = "id")
@Table(name = "role")
@Entity
public class Role extends DefaultEntity {
	
	@Column(length = 191, nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<AccountRole> accountRole = new ArrayList<>();
	
	@Builder
	public Role(String id, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, 
				String name, List<AccountRole> accountRole) {
		super(id, createdAt, createdBy, updatedAt, updatedBy);
		this.name = name;
		this.accountRole = accountRole;
	}
	
//	public RoleTypeCreateDto toCreateDto() {
//		return RoleTypeCreateDto.builder()
//				  	     	    .typeName(typeName)
//				  	     	    .build();
//	}
	
}