package com.gvt.cdr.bo.Funcionalidades;

import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoPcon;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoXe;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;

// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail
// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail
// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail
// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail
// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail

public class BuscaInfo440RetailFavoritos {

	private BuscaInfoXe xe = new BuscaInfoXe().getInstance();
	private BuscaInfoPcon pcon = new BuscaInfoPcon().getInstance();
	
	public void executeBusca(){

		List<Instancia440RetailVO> instancias = xe.carregaListaInstanciasAnaliseFavoritos();
		
		Instancia440RetailVO instancia = null;

		Log.info("> Total de casos: " + instancias.size(), this);
		
		for(int x=0; x<instancias.size(); x++){
			
			instancia = (Instancia440RetailVO) instancias.get(x);
			// Busca o server_id da inst�ncia
			pcon.buscaInfoFavoritos(instancia);

			
			System.out.println(x + " " + instancia.getExternalId() + " " + instancia.getStatusFavorito()
					                                               + " " + instancia.getDataFavorito());

			// Grava informa��o no banco
			xe.insereInfo440Favorito(instancia);

		}

		this.updatesEspecificosFavorito();
		
		
	}

	private void updatesEspecificosFavorito(){

		// Atualiza casos que est�o ok no COL, basta corrigir os CDRs
		xe.updateFavoritosColOk();
		// Atualiza casos que devem ser corrigidos no COL e tamb�m os CDRs
		xe.updateFavoritosColErro();
		// A query seleciona os casos dos �ltimos 30 dias para corre��o no COL e geral para corre��o de CDRs
//		xe.updateFavoritosColOutros();
		
	}

}
