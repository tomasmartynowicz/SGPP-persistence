package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.List;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.Importe;

public class ImporteDAO extends PostgreSQLClient<Importe>{
	
	public ImporteDAO() {
		super();
	}
	
	public Importe traerImportePorObraSocialYTipoPractica(String obraSocial, String tipoPractica) {
		return traerObjeto(String.format("select i from Importe i"
				+ " join i.obraSocial os"
				+ " join i.tipoPractica tp"
				+ " where os.razonSocial='%s' and tp.tipo='%s'", obraSocial, tipoPractica));
	}
	
	public List<Importe> traerImportePorObraSocial(String obraSocial) {
		return traerListaObjeto(String.format("select i from Importe i"
				+ " join i.ObraSocial os"
				+ " where os.razonSocial='%s'",  obraSocial));
	}
}
