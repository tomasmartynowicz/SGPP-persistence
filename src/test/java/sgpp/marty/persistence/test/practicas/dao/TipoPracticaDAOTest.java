package sgpp.marty.persistence.test.practicas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgpp.marty.persistence.postgres.practicas.dao.TipoPracticaDAO;
import sgpp.marty.persistence.test.AbstractTest;
import sgpp.marty.practicas.model.TipoPractica;

public class TipoPracticaDAOTest extends AbstractTest {

	private final TipoPracticaDAO tipoPracticaDao = new TipoPracticaDAO();
	private static final String TIPO_CONSULTA = "Consulta";
	
	@Before
	public void setup() {
		try {
			tipoPracticaDao.crear(new TipoPractica(TIPO_CONSULTA));
		} catch(Exception e) {
			// Tipo de practica ya existe en base de datos
		}
		
	}
	
	@Test
	public void traerTipoPracticaPorTipo() {
		TipoPractica tp = tipoPracticaDao.traerTipoPracticaPorTipo(TIPO_CONSULTA);
		
		assertNotNull(tp);
		assertEquals("Consulta", tp.getTipo());

	}
	
	@Test
	public void traerTodosTipoPractica() {
		List<TipoPractica> tipoPractica = tipoPracticaDao.traerTipoPracticas(); 
		assertNotEmptyList(tipoPractica);
		assertContainsElement(tipoPractica, new TipoPractica(TIPO_CONSULTA));
	}
}
