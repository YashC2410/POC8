package com.poc8.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project extends StudentAuditModel{
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer projectId;
@Column(name="project_name",nullable=false,unique=false)
private String projectName;
@Column(name="project_tect",nullable=false,unique=false)
private String projectTech;
@ManyToOne(fetch=FetchType.LAZY,optional=true)
@JoinColumn(name="student_id",nullable=false)
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
private Student student;
}
