package com.example.SpringProject_1.dataModels;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private long id;

	@Setter @Getter
	private String firstname;

	@Setter @Getter
	private String lastname;

	public Client() {}

	public Client(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Client(long id, String firstname, String lastname) {
		this(firstname, lastname);
		this.id = id;
	}

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
