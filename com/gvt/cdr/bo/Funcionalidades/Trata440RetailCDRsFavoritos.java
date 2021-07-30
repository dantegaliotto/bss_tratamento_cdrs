package com.gvt.cdr.bo.Funcionalidades;

import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoPcon;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoPgen;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoXe;
import com.gvt.cdr.bo.Funcionalidades.vo.CdrVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
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

public class Trata440RetailCDRsFavoritos {

	private BuscaInfoXe xe = new BuscaInfoXe().getInstance();
	private BuscaInfoPgen pgen = new BuscaInfoPgen().getInstance();
	
	public void executeBusca(){

		List<CdrVO> cdrs = xe.carregaListaCDRsTratamentoFavoritos();
		
		CdrVO cdr = null;

		Log.info("> Total de casos: " + cdrs.size(), this);
		
		for(int x=0; x<cdrs.size(); x++){
			
			cdr = (CdrVO) cdrs.get(x);
			// Busca o server_id da instância
			pgen.buscaInfoCDRFavoritos(cdr);
			
			System.out.println(x + " " + cdr.getExternalId() + " " + cdr.getExternalTrackingId() + " " + cdr.getTipoUsoCorreto());

			// Grava informação no banco
			xe.insereInfo440CDRFavorito(cdr);

		}

		this.updatesEspecificosFavorito();
		
		
	}

	private void updatesEspecificosFavorito(){

		// Atualiza quem não encontrou no log da PGEN
		xe.updateNaoEncontradosNaPgen();
		// Monta o comando para atualização do CDR na PBCAT
		xe.updateFavoritosComandoPBCAT();
		
	}

}
