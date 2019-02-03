package sgpp.marty.persistence.test.practicas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import sgpp.marty.persistence.postgres.practicas.dao.ImporteDAO;
import sgpp.marty.persistence.postgres.practicas.dao.ObraSocialDAO;
import sgpp.marty.persistence.postgres.practicas.dao.TipoPracticaDAO;
import sgpp.marty.practicas.model.Importe;
import sgpp.marty.practicas.model.ObraSocial;
import sgpp.marty.practicas.model.TipoPractica;

public class ImporteDAOTest {
	
	private static final ImporteDAO importeDao = new ImporteDAO();
	private static final ObraSocialDAO obraSocialDao = new ObraSocialDAO();
	private static final TipoPracticaDAO tipoPracticaDao = new TipoPracticaDAO();
	
	private static final String OBRA_SOCIAL = "os_importe";
	private static final String TIPO_PRACTICA = "tp_importe";
	private static final double ARANCEL = 200;
	private static final double HONORARIOS = 500;
	private static final Date FECHA_CREACION = new Date();

	@Before
	public void setup() {
		ObraSocial os = new ObraSocial(OBRA_SOCIAL);
		try {
			obraSocialDao.crear(os);
		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
			os = obraSocialDao.traerObraSocialPorRazonSocial(OBRA_SOCIAL);
		}
		
		TipoPractica tp = new TipoPractica(TIPO_PRACTICA);
		try {
			tipoPracticaDao.crear(tp);
		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
			tp = tipoPracticaDao.traerTipoPracticaPorTipo(TIPO_PRACTICA);
		}
		
		Importe importe = new Importe();
		importe.setObraSocial(os);
		importe.setTipoPractica(tp);
		importe.setArancel(ARANCEL);
		importe.setHonorarios(HONORARIOS);
		importe.setFechaCreacion(FECHA_CREACION);
		
		try {
			importeDao.crear(importe);
		} catch(Exception e) {
			// Registro ya existe en base de datos
			e.printStackTrace();
		}
	}
	
	@Test
	public void traerImportePorObraSocialYTipoPractica() {
		Importe i = importeDao.traerImportePorObraSocialYTipoPractica(OBRA_SOCIAL, TIPO_PRACTICA);
		
		assertNotNull(i);
		assertEquals(ARANCEL, i.getArancel(), 0.01);
		assertEquals(HONORARIOS, i.getHonorarios(), 0.01 );
	}
	
	
}
