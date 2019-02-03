package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.Date;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.ImporteHistorico;

public class ImporteHistoricoDAO extends PostgreSQLClient<ImporteHistorico> {

	public ImporteHistoricoDAO() {
		super();
	}
	
	public ImporteHistorico traerImportePorObraSocialYTipoPracticaYFecha(String obraSocial, String tipoPractica, Date fecha) {
		return traerObjeto(String.format("from ImporteHistorico ih"
				+ " where ih.obraSocial='%s'"
				+ " and ih.tipoPractica='%s'"
				+ " and  %d >= fechaInicio and %d <= fechaVencimiento", obraSocial, tipoPractica, fecha.getTime(), fecha.getTime()));
	}
}
