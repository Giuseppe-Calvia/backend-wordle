package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.ebean.annotation.WhoCreated;
import io.ebean.annotation.WhoModified;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public abstract class AbstractAuditable extends Model {

	@JsonIgnore
	@Version
	@Schema(hidden = true)
	protected  Long _version;

	@JsonIgnore
	@WhenCreated
	@Schema(hidden = true)
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date _dataCreazione;

	@JsonIgnore
	@Schema(hidden = true)
	@Column(updatable = false)
	@WhoCreated
	protected String _utenteCreazione;

	@JsonIgnore
	@Schema(hidden = true)
	@WhenModified
	@Temporal(TemporalType.TIMESTAMP)
	protected Date _dataModifica;

	@JsonIgnore
	@Schema(hidden = true)
	@WhoModified
	protected String _utenteModifica;
}
