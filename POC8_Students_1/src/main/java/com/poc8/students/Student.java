package com.poc8.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends StudentAuditModel{
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="student_id")
private Integer id;
@NotEmpty
@Column(name="student_fname",nullable=false,length=60)
private String fname;
@NotEmpty
@Column(name="student_lname",nullable=false,length=60)
private String lname;
@NotEmpty
@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Email Should be Valid with atleast one @ and .")
@NotEmpty
@Column(name="student_email",nullable=false,unique=true)
private String email;
@Pattern(regexp="^[a-zA-Z0-9]{10}",message="Contact Number should only contain digits and should be of length 10")
@NotEmpty
@Column(name="student_contact",nullable=false,unique=true)
private String contact;
@Column(name="photo",nullable=true,length=64)
private String photos;
private String photosImagePath;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getPhotos() {
	return photos;
}
public void setPhotos(String photos) {
	this.photos = photos;
}
@Transient
public String getPhotosImagePath() {
	 if (photos == null || id == null) return null;
     return "/students-photos/" + id + "/" + photos;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}
