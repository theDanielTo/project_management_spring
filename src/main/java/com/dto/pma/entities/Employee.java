package com.dto.pma.entities;

import com.dto.pma.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
  @SequenceGenerator(name="employee_seq", sequenceName="employee_seq", allocationSize = 1,initialValue = 1)
  private long employeeId;

  @NotNull
  @Size(min=2, max=50)
  private String firstName;

  @NotNull
  @Size(min=1, max=50)
  private String lastName;

  @NotNull
  @Email
  @UniqueValue
  private String email;

  @ManyToMany(
    cascade={
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.PERSIST
    },
    fetch = FetchType.LAZY)
  @JoinTable(name = "project_employee",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id"))
  @JsonIgnore
  private List<Project> projects;

  public Employee() { }

  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public long getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public List<Project> getProjects() {
    return projects;
  }
  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
}
