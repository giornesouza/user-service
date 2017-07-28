package com.gs.user.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private String favouriteTeam;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserCampaign> campaigns = new ArrayList<>();

    public User() {};

    public User(Long id, String name, String email, LocalDate birthDate, String favouriteTeam) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.birthDate = birthDate;
	this.favouriteTeam = favouriteTeam;
    }

    public User(String name, String email, LocalDate birthDate, String favouriteTeam) {
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

    public List<UserCampaign> getCampaigns() {
	return campaigns;
    }

    public void setCampaigns(List<UserCampaign> campaigns) {
	this.campaigns = campaigns;
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
	User other = (User) obj;
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
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate
		+ ", favouriteTeam=" + favouriteTeam + "]";
    }

}
