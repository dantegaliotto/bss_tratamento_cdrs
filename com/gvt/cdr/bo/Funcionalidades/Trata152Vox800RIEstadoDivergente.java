package com.gvt.cdr.bo.Funcionalidades;

import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoArbor;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoXe;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia152Vox800RIUFVO;
import com.gvt.util.Log;

// ESTE JAVA RODA DEPOIS DO BuscaInfo440Retail 
//                     e do BuscaInfo440RetailFavoritos
/* PROCEDIMENTO INICIAL:
 * 
create table bss_440_cdrs_favoritos as
select b.external_id,
       cw.msg_id, cw.msg_id2, cw.msg_id_serv,
       cw.ext_tracking_id,
       cw.valor,
       cw.tipo_uso tu1,
       cast(null as number) as tu_ok,
       cast(null as varchar2(300)) as comando_pbcat
from bss_440_retail b
join cdrs_work cw on cw.external_id = b.external_id
                 and cw.tipo_uso in (271,412,643)
where b.acao_it like 'Corrigir tipo_uso dos CDRs%'
 * 
 */

public class Trata152Vox800RIEstadoDivergente {

	private BuscaInfoXe    xe    = new BuscaInfoXe().getInstance();
	private BuscaInfoArbor kenan = new BuscaInfoArbor().getInstance();
	
	public void executeBusca(){

		List<Instancia152Vox800RIUFVO> cdrs = xe.carregaListaVOX800RiUF();
		
		Instancia152Vox800RIUFVO instancia = null;

		Log.info("> Total de casos: " + cdrs.size(), this);
		
		for(int x=0; x<cdrs.size(); x++){
			
			instancia = (Instancia152Vox800RIUFVO) cdrs.get(x);
			// Busca o server_id da instância
			kenan.retornaUfCorreta(instancia);
			
			System.out.println(x + " " + instancia.getExternalId() + " " + instancia.getValor() + " " + instancia.getExternalIdCorreto() );

			instancia.setComandoCorrecao(
				"update cdr_data_work set external_id = '" + instancia.getExternalIdCorreto() +
				"', annotation = trunc(sysdate) || ' - G0032485 - External_id atualizado de " + instancia.getExternalId() +
				" para " + instancia.getExternalIdCorreto() +
				"' where external_id = '" + instancia.getExternalId() +
				"' and miu_disp_status <> 0 and miu_disp_code in (0,1) and miu_error_code1 = 152;"); 
			System.out.println(instancia.getComandoCorrecao());
			
			// Grava informação no banco
			xe.insereComandosCorrecaoVox800RIUF(instancia);

		}

//		this.updatesEspecificosFavorito();
		
		
	}

	private void updatesEspecificosFavorito(){

		// Monta o comando para atualização do CDR na PBCAT
		xe.updateFavoritosComandoPBCAT();
		
	}

}
