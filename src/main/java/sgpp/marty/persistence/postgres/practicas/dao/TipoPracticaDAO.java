package sgpp.marty.persistence.postgres.practicas.dao;

import java.util.List;

import sgpp.marty.persistence.postgres.PostgreSQLClient;
import sgpp.marty.practicas.model.TipoPractica;

public class TipoPracticaDAO extends PostgreSQLClient<TipoPractica> {
	
	public TipoPracticaDAO() {
		super();
	}
	
	public List<TipoPractica> traerTipoPracticas() {
		return this.traerListaObjeto("from TipoPractica");
	}
	
	public TipoPractica traerTipoPracticaPorTipo(String tipo) {
		return this.traerObjeto("from TipoPractica tp where tp.tipo='" + tipo + "'" );
	}
	
	public TipoPractica traerTipoPracticaPorId(int id) {
		return this.traerObjeto("from TipoPractica tp where tp.id=" + id );
	}

}
