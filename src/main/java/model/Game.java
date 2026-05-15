package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.enumerator.StatoPartita;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Game extends AbstractAuditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private int lunghezzaSelezionataDellaParola;

	@NotNull
	@Column(nullable = false)
	private int tentativiMassimi;

	@OneToMany(cascade = CascadeType.ALL)
	List<Tentativo> tentativi = new ArrayList<>();;

	@OneToOne(cascade = CascadeType.ALL)
	Parola parola;

	@Column(nullable = false)
	StatoPartita statoPartita;

}
