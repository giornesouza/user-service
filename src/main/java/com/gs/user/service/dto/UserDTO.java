package com.gs.user.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.user.domain.User;


public class UserDTO {
    
    private Long id;
    
    @NotNull
    @NotBlank
    private String name;
    
    @NotNull
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;
    
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private String favouriteTeam;
    
    public UserDTO() {};
    
    public UserDTO(User user) {
	this.id = user.getId();
	this.name = user.getName();
	this.email = user.getEmail();
	this.birthDate = user.getBirthDate();
	this.favouriteTeam = user.getFavouriteTeam();
    }

    public UserDTO(String name, String email, LocalDate birthDate, String favouriteTeam) {
	this.name = name;
	this.email = email;
	this.birthDate = birthDate;
	this.favouriteTeam = favouriteTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFavouriteTeam() {
        return favouriteTeam;
    }

    public void setFavouriteTeam(String favouriteTeam) {
        this.favouriteTeam = favouriteTeam;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserDTO other = (UserDTO) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate
		+ ", favouriteTeam=" + favouriteTeam + "]";
    }

 
}
