package com.example.springProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client")
@Getter @Setter
public class Client implements Comparable<Client>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	private String firstname;

	private String lastname;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job")
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
	}

	@Override
	public int compareTo(Client c) {
		if (this.getId() == c.getId()) {
			return 0;
		}
		return this.getId() - c.getId() > 0 ? 1 : -1;
	}
}
