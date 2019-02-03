package sgpp.marty.persistence.test.practicas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgpp.marty.persistence.postgres.practicas.dao.PacienteDAO;
import sgpp.marty.persistence.postgres.practicas.dao.PracticaDAO;
import sgpp.marty.persistence.test.AbstractTest;
import sgpp.marty.practicas.model.Paciente;
import sgpp.marty.practicas.model.Practica;

public class PracticaDAOTest extends AbstractTest {

	private static final PracticaDAO practicaDao = new PracticaDAO();
	private static final PacienteDAO pacienteDao = new PacienteDAO();
	private static final String OBRA_SOCIAL = "os_practica";
	private static final String TIPO_PRACTICA = "tp_practica";
	private static final Date FECHA = new Date();
	private static final String PACIENTE_NOMBRE = "nombre_practica";
	private static final String PACIENTE_APELLIDO = "apellido_practica";
	private static final String PACIENTE_CARNET = "carnet_practica";
	private static final String PACIENTE_CUIL = "cuil_practica";
	private static final double ARANCEL = 200;
	private static final double HONORARIOS = 500;
	
	@Before
	public void setup() {
		Paciente paciente = new Paciente();
		paciente.setNombre(PACIENTE_NOMBRE);
		paciente.setApellido(PACIENTE_APELLIDO);
		paciente.setCarnet(PACIENTE_CARNET);
		try {
			pacienteDao.crear(paciente);
		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
		}
		
		Practica practica = new Practica();
		practica.setObraSocial(OBRA_SOCIAL);
		practica.setTipoPractica(TIPO_PRACTICA);
		practica.setFecha(FECHA);
		practica.setPaciente(paciente);
		practica.setArancel(ARANCEL);
		practica.setHonorarios(HONORARIOS);
		try {
			practicaDao.crear(practica);
		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
		}
	}
	
	@Test
	public void traerPracticaPorPacienteYFecha() {
		Practica p = practicaDao.traerPracticaPorPacienteYFecha(PACIENTE_CARNET, FECHA);
		
		assertNotNull(p);
		assertEquals(OBRA_SOCIAL, p.getObraSocial());
		assertEquals(TIPO_PRACTICA, p.getTipoPractica());
	}
	
	@Test
	public void traerPracticaPorPaciente() {
		List<Practica> practicas = practicaDao.traerPracticaPorPaciente(PACIENTE_CARNET);
		
		assertNotNull(practicas);
		this.assertNotEmptyList(practicas);

	}
}
