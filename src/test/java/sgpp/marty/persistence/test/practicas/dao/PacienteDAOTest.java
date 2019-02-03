package sgpp.marty.persistence.test.practicas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgpp.marty.persistence.postgres.practicas.dao.ObraSocialDAO;
import sgpp.marty.persistence.postgres.practicas.dao.PacienteDAO;
import sgpp.marty.persistence.test.AbstractTest;
import sgpp.marty.practicas.model.ObraSocial;
import sgpp.marty.practicas.model.Paciente;

public class PacienteDAOTest extends AbstractTest {

	private final PacienteDAO pacienteDao = new PacienteDAO();
	private static final String CARNET_TEST = "1234";
	private static final String NOMBRE_TEST = "elena";
	private static final String APELLIDO_TEST = "martinez";
	private static final String CUIL_TEST = "12345";
	private static final String OBRA_SOCIAL_TEST = "paciente_test";
	
	private Paciente p;
	
	@Before
	public void setup() {
		ObraSocialDAO osDao = new ObraSocialDAO();
		ObraSocial os = new ObraSocial(OBRA_SOCIAL_TEST);
		p = new Paciente();
		p.setCarnet(CARNET_TEST);
		p.setNombre(NOMBRE_TEST);
		p.setApellido(APELLIDO_TEST);
		p.setCuil(CUIL_TEST);
		p.setObraSocial(os);
		try {
			osDao.crear(os);

		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
		}
		try {
			pacienteDao.crear(p);

		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
		}

	}
	
	@Test
	public void traerPacientePorCarnet() {
		Paciente p = pacienteDao.traerPacientePorCarnet(CARNET_TEST);
		
		assertNotNull(p);
		assertEquals(p.getCarnet(), CARNET_TEST);
	}
	
	@Test
	public void traerPacientePorNombreYApellido() {
		List<Paciente> pacientes = pacienteDao.traerPacientePorNombreYApellido(NOMBRE_TEST, APELLIDO_TEST);
		
		assertNotEmptyList(pacientes);
		assertContainsElement(pacientes, p);
	}
	
	@Test
	public void traerPacientePorNombre() {
		List<Paciente> pacientes = pacienteDao.traerPacientePorNombre(NOMBRE_TEST);
		
		assertNotEmptyList(pacientes);
		assertContainsElement(pacientes, p);
	}
	
	@Test
	public void traerPacientePorApellido() {
		List<Paciente> pacientes = pacienteDao.traerPacientePorApellido(APELLIDO_TEST);
		
		assertNotEmptyList(pacientes);
		assertContainsElement(pacientes, p);
	}
	
	@Test
	public void traerPacientePorCuil() {
		Paciente p = pacienteDao.traerPacientePorCuil(CUIL_TEST);
		
		assertNotNull(p);
		assertEquals(p.getCuil(), CUIL_TEST);
	}
	
	@Test
	public void traerPacientePorObraSocial() {
		List<Paciente> pacientes = pacienteDao.traerPacientePorObraSocial(OBRA_SOCIAL_TEST);
		
		assertNotEmptyList(pacientes);
		assertContainsElement(pacientes, p);
	}
	
	@Test
	public void failTraerPacientePorObraSocial() {
		List<Paciente> pacientes = pacienteDao.traerPacientePorObraSocial("Falsa obraSocial");
		
		assertDoesntContainsElement(pacientes, p);
	}
	
	
}
