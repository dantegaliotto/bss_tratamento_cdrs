package com.gvt.cdr.bo.Funcionalidades;

import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoSiebel8;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoXe;
import com.gvt.cdr.bo.Funcionalidades.dao.BuscaInfoArbor;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;

// PROCEDIMENTO INICIAL
/* 
create table bss_440_retail as
select trunc(sysdate) created, external_id,
       cast(null as VARCHAR2(144)) as conta,
       cast(null as number(1)) as server_id,
       cmf_bill_period ciclo, 
       account_no,
       trunc(sum(valor),2) valor,
       count(*) qtde,
       min(trans_dt) cdrini,
       max(trans_dt) cdrfim,
       min(tipo_uso) tu1,
       max(tipo_uso) tu2,
       cast(null as date) as data_guiador,
       cast(null as VARCHAR2(50)) as irma_guiador,
       cast(null as VARCHAR2(100)) as plano,
       cast(null as VARCHAR2(50)) as portfolio,
       cast(null as date) as dt_gui_inat1,
       cast(null as date) as dt_gui_inat2,
       cast(null as VARCHAR2(100)) as ordem_ativ,
       cast(null as VARCHAR2(100)) as ordem_inat,
       cast(null as VARCHAR2(50)) as status_favorito,
       cast(null as date) as data_favorito,
       cast(null as VARCHAR2(100)) as cenario_guiador,
       cast(null as VARCHAR2(100)) as cenario_analise,
       cast(null as varchar2(100)) as acao_it
from cdrs_work
--where cenario = 'Instância S8 com erro plano Kenan - RETAIL'
where cenario = 'Plano LOREN com CDRs Favoritos'
and tipo_erro = 440
and trans_dt >= trunc(sysdate)-120
group by external_id, cmf_bill_period, account_no;

*
*/

public class BuscaInfo440Retail {

	private BuscaInfoXe xe = new BuscaInfoXe().getInstance();
	private BuscaInfoArbor arbor = new BuscaInfoArbor().getInstance();
	private BuscaInfoSiebel8 siebel8 = new BuscaInfoSiebel8().getInstance();
	
	public void executeBusca(){

		List<Instancia440RetailVO> instancias = xe.carregaListaInstanciasAnalise();
		
		Instancia440RetailVO instancia = null;

		Log.info("> Total de casos: " + instancias.size(), this);
		
		for(int x=0; x<instancias.size(); x++){
			
			instancia = (Instancia440RetailVO) instancias.get(x);
			// Busca o server_id da instância
			instancia.setServerId(arbor.buscaServerId(instancia.getExternalId()));
            if (instancia.getServerId() == 3 || instancia.getServerId() == 4){
	            // Busca no Kenan a conta
				arbor.buscaConta(instancia);
				// Busca no Kenan a conta
				arbor.buscaInstanciaIrmaComGuiador(instancia);
				// Busca no Kenan a informação sobre o guiador do menor tipo_uso 
				arbor.buscaGuiador(instancia);
				// Busca no Kenan as informações sobre o último guiador inativo antes do inicio do problema 
				arbor.buscaGuiadorInativo(instancia);
				// Busca no Kenan as qtde de servicos para a instancia 
				//arbor.buscaQtdeServicos(instancia);
            }
			// Busca no Siebel o plano atual
			siebel8.retornaPlanoRetail(instancia);		
			
			System.out.println(x + " " + instancia.getExternalId() + " " + instancia.getTipoUso() 
					                                               + " " + instancia.getServerId()
					                                               + " " + instancia.getDtGuiador() 
					                                               + " " + instancia.getPlano());

			// Grava informação no banco
			xe.insereInfo440Retail(instancia);

		}

		this.updatesEspecificos();
		
	}

	private void updatesEspecificos(){

		// Atualiza o portfolio para o LOREN
		xe.updatePortfolio();
		// Atualiza o status do guiador
		xe.updateSemGuiador();
		// Atualiza o cenário de PIDS Duplicados
		xe.updatePidsDuplicados();
		// Atualiza cenário de instância irmã gerando CDRs
		xe.updateInstanciaIrma();
		// Atualiza o cenário do Catarina REQ1634425
		xe.updateCatarinaREQ1634425();
		// Atualiza o cenário de LOREN gerando favoritos
		xe.updateLorenFavoritos();
		// Atualiza clientes sem info da conta
		xe.updateInativoNoKenan();
	}

}
