package com.gvt.cdr.bo.Funcionalidades;

import java.sql.Date;
import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoSiebel8;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoXe;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoArbor;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;

public class BuscaInfoPidDuplicado {

	private BuscaInfoXe xe = new BuscaInfoXe().getInstance();
	private BuscaInfoArbor arbor = new BuscaInfoArbor().getInstance();
	private BuscaInfoSiebel8 siebel8 = new BuscaInfoSiebel8().getInstance();
	
	public void executeBusca(String sequencial) {

		List<Instancia440RetailVO> instancias = xe.carregaListaInstancias440PidDuplicado();
		
		Instancia440RetailVO instancia = null;

		Log.info("> Total de casos: " + instancias.size(), this);
		
		for(int x=0; x<instancias.size(); x++){
			
			instancia = (Instancia440RetailVO) instancias.get(x);
			// Busca no Kenan a informação sobre o guiador do menor tipo_uso 
			arbor.buscaGuiador(instancia);
			// Busca no Siebel o plano atual
			siebel8.retornaPlanoRetail(instancia);
			
			
			System.out.println(x + " " + instancia.getExternalId() + " " + instancia.getTipoUso() 
					                                               + " " + instancia.getServerId()
					                                               + " " + instancia.getDtGuiador() 
					                                               + " " + instancia.getPlano());


			// Grava informação no banco
			xe.insereInfo440Retail(instancia);

		}

	}

	
	
}
