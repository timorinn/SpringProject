package com.example.springProject.entities;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
public class Job implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private Integer id;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = false)
	@Getter @Setter
	private List<Client> clients = new ArrayList<>();

	@Column(name = "name")
	@Getter @Setter
	private String name;
}
