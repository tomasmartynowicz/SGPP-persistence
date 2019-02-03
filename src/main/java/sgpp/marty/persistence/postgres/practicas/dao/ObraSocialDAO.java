package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.List;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.ObraSocial;

public class ObraSocialDAO extends PostgreSQLClient<ObraSocial> {

	public ObraSocialDAO() {
		super();
	}
	
	public ObraSocial traerObraSocialPorRazonSocial(String razonSocial) {
		return this.traerObjeto("from ObraSocial os where os.razonSocial='" + razonSocial + "'" );
	}
	
	public List<ObraSocial> traerObrasSociales() {
		return this.traerListaObjeto("from ObraSocial");
	}
}
