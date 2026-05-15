package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.enumerator.StatoLettera;

@Setter
@Getter
@Entity
public class RisultatoLettera extends AbstractAuditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String lettera;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatoLettera stato;
}
