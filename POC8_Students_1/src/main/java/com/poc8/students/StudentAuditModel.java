package com.poc8.students;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		value= {"addedAt","updatedAt"},
		allowGetters=true
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAuditModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="addedAt",nullable=false,updatable=false)
	@CreatedDate
	private Date addedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatedAt",nullable=false,updatable=true)
	@LastModifiedDate
	private Date updatedAt;
	
	

}
