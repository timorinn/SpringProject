package com.example.SpringProject_1.dataModels;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter @Getter
	private long id;

	@Setter @Getter
	private String firstname;

	@Setter @Getter
	private String lastname;

	public Client() {}

	public Client(long id, String firstname, String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}

	@Override
	public boolean equals(Object obj) {
		Client objClient;

		if (this == obj) return true;

		if (obj.getClass() != this.getClass()) return false;

		objClient = (Client) obj;

		return (Objects.equals(this.id, objClient.id)
				&& Objects.equals(this.firstname, objClient.firstname)
				&& Objects.equals(this.lastname, objClient.lastname));
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}
}
