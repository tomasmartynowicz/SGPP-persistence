package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.List;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.Paciente;

public class PacienteDAO extends PostgreSQLClient<Paciente> {
	
	public PacienteDAO() {
		super();
	}
	
	public Paciente traerPacientePorCarnet(String carnet) {
		return this.traerObjeto("from Paciente p where p.carnet='" + carnet + "'");
	}
	
	public Paciente traerPacientePorCuil(String cuil) {
		return this.traerObjeto("from Paciente p where p.cuil='" + cuil + "'");
	}
	
	public List<Paciente> traerPacientePorNombreYApellido(String nombre, String apellido) {
		return this.traerListaObjeto(String.format("from Paciente p where p.nombre='%s' and p.apellido='%s'", nombre, apellido));
	}
	
	public List<Paciente> traerPacientePorNombre(String nombre) {
		return this.traerListaObjeto(String.format("from Paciente p where p.nombre='%s'", nombre));
	}
	
	public List<Paciente> traerPacientePorApellido(String apellido) {
		return this.traerListaObjeto(String.format("from Paciente p where p.apellido='%s'", apellido));
	}
	
	public List<Paciente> traerPacientePorObraSocial(String razonSocial) {
		return this.traerListaObjeto(String.format("select p from Paciente p "
				+ "join p.obraSocial os "
				+ "where os.razonSocial='%s' ", razonSocial));
	}
	
	
}
