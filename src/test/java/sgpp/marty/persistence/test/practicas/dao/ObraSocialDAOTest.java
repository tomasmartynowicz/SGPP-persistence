package sgpp.marty.persistence.test.practicas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgpp.marty.persistence.postgres.practicas.dao.ObraSocialDAO;
import sgpp.marty.persistence.test.AbstractTest;
import sgpp.marty.practicas.model.ObraSocial;

public class ObraSocialDAOTest extends AbstractTest {
	
	private final ObraSocialDAO obraSocialDao = new ObraSocialDAO();
	private static final String RZN_SOCIAL_TEST = "test";
	
	@Before
	public void setup() {
		try {
			obraSocialDao.crear(new ObraSocial(RZN_SOCIAL_TEST));
		} catch(Exception e) {
			// Tipo de practica ya existe en base de datos
			e.printStackTrace();
		}
	}
	
	@Test
	public void traerObraSocialPorRzonSocial() {
		ObraSocial os = obraSocialDao.traerObraSocialPorRazonSocial(RZN_SOCIAL_TEST);
		
		assertNotNull(os);
		assertEquals(RZN_SOCIAL_TEST, os.getRazonSocial());

	}
	
	@Test
	public void traerTodasObrasSociales() {
		List<ObraSocial> obrasSociales = obraSocialDao.traerObrasSociales(); 
		assertNotEmptyList(obrasSociales);
		assertContainsElement(obrasSociales, new ObraSocial(RZN_SOCIAL_TEST));
	}
}
