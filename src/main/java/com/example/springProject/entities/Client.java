package com.example.springProject.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Setter @Getter
	private long id;

	@Setter @Getter
	private String firstname;

	@Setter @Getter
	private String lastname;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job")
	@Setter @Getter
	private Job job;


	public Client() {}


	public Client(@NotNull long id, String firstname, String lastname, Job job) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.job = job;
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
		return String.format("Client {id=%s, firstname=%s, lastname=%s, job=%s}",
				id, firstname, lastname, job == null ? "unemployed" : job.getName());
//		return "Client{" +
//				"id=" + id +
//				", firstname='" + firstname + '\'' +
//				", lastname='" + lastname + '\'' +
//				'}';
	}
}
