package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.Date;
import java.util.List;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.Practica;

public class PracticaDAO extends PostgreSQLClient<Practica> {

	public PracticaDAO() {
		super();
	}
	
	public Practica traerPracticaPorPacienteYFecha(String carnet, Date fecha) {
		return traerObjeto(String.format("select p from Practica p "
				+ " join p.paciente pc"
				+ " where pc.carnet=?0 and p.fecha=?1"),carnet, fecha);
	}
	
	public List<Practica> traerPracticaPorPaciente(String carnet) {
		return traerListaObjeto(String.format("select p from Practica p "
				+ " join p.paciente pc"
				+ " where pc.carnet=?0"),carnet);
	}
}
