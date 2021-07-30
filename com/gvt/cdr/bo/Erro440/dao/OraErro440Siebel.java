package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Erro440.vo.RangeVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Siebel {
	
	private static OraErro440Siebel instance;
	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public OraErro440Siebel() {

	}
	
	public OraErro440Siebel getInstance() {
		if (instance == null)
			instance = new OraErro440Siebel();

		return instance;
	}
	
	public String retornaRpon(String instancia){
		
		String rpon = "";
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaRpon");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				rpon = rs.getString("row_id");
				
			}

			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
		return rpon;
		
	}

	
	public String retornaRponPeloRange(String instancia){
		
		String rpon = "";
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaRponPeloRange");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				rpon = rs.getString("rpon");
				
			}

			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
		return rpon;
		
	}

	
	public List<String> buscaRponsDaInstancia(InstanciaSiebelVO instanciaSiebel){
		
		List<String> listaRpons = new ArrayList<String>();
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaTodosRpons");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel.getDesignador());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				String rpon = rs.getString("row_id");
				listaRpons.add(rpon);
				
			}
			
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
		return listaRpons;
		
	}
	
	public void retornaTipoInstanciaEData(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaDtInstalacaoETipo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				// alterado para pegar a data da instancia do kenan
				instancia.setDtInstanciaSiebel(rs.getDate("end_dt"));
				instancia.setTipoInstanciaSiebel(rs.getString("name"));
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}
	
	public void retornaPlanoRetail(InstanciaSiebelVO instancia, String status){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoRetail");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			//pstmt.setString(2, instancia.getRpon());
			//pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("R");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	public boolean verificaDuplicidadePlanos(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelContaPlanos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			//pstmt.setString(2, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				// SE O CLIENTE TIVER MAIS DE UM PLANO RETORNA VERDADEIRO
				if (rs.getInt("contagem")>1){
					retorno = true;
				}
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	
	public void retornaPlanoRetailLavoisier(InstanciaSiebelVO instancia, String status){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoRetailLavoisier");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			//pstmt.setString(2, instancia.getRpon());
			//pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("L");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	
	
	
	public void retornaPlanoTv(InstanciaSiebelVO instancia, String status){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoTv");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			//pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("TV");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}
	
	public void retornaPlanoRetailVc(InstanciaSiebelVO instancia, String status){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoRetailVc");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setSvc(rs.getString("name"));
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	
	
	public void retornaPlanoCorp(InstanciaSiebelVO instancia, String status){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoCorp");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("C");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}


	public boolean retornaProduto0800(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProduto0800");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("C");
				retorno = true;
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	public boolean retornaProduto0800RI(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProduto0800RI");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setPlano(rs.getString("x_lista_de_valores").toUpperCase());
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("C");
				retorno = true;
				
			}
			
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	public boolean retornaProdutoRI(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaProdutoRI");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setPlano(rs.getString("name").toUpperCase());
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("C");
				retorno = true;
				
			}
			
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	
	public void retornaDesconto0800(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaDesconto0800");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				instancia.setSlocal(rs.getString("name"));
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	
	
	public void retornaPlanoVOXDDR(InstanciaSiebelVO instancia){
	
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaRowIdDataPlanoVOXDDR");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setPlano("VOXDDR");
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("C");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}


	public void retornaRange(InstanciaSiebelVO instancia){
		
		List<RangeVO> ranges = new ArrayList<RangeVO>();
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaRange");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			RangeVO range = null;
			
			while(rs.next()){
				
				range = new RangeVO();
				
				range.setRangeInicial(rs.getString("x_range_inicial"));
				range.setRangeFinal(rs.getString("x_range_final"));
			
				ranges.add(range);
				
			}
			
			instancia.setRanges(ranges);
			
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}
	
	public void retornaParametros(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaParametros");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setSlocal(rs.getString("slocal"));
				instancia.setSvc(rs.getString("svc"));
				instancia.setSldn(rs.getString("sldn"));
				instancia.setSldi(rs.getString("sldi"));
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	public boolean procuraRegistroTabelaSel(InstanciaSiebelVO instanciaSiebel){

		boolean retorno = false;
		
		String query = "";
		if (instanciaSiebel.getTipoPlano().equals("C"))
			query = QueryWarehouse.getQuery("erro440siebelProcuraDadosInterfaceCorporate");
		else
			query = QueryWarehouse.getQuery("erro440siebelProcuraDadosInterfaceRetail");
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel.getRpon());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				retorno = true;
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	
	public void insereRegistroTabelaSel(InstanciaSiebelVO instanciaSiebel) {

		String query = "";
		if (instanciaSiebel.getTipoPlano().equals("C"))
			query = QueryWarehouse.getQuery("erro440siebelInsereDadosInterfaceCorporate");
		else
			query = QueryWarehouse.getQuery("erro440siebelInsereDadosInterfaceRetail");
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel.getRpon());

			pstmt.executeUpdate();
			
			pstmt.close();
						
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
			return;
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return;
	}

	public void updateRegistroTabelaSel(InstanciaSiebelVO instanciaSiebel) {

		String query = "";
		if (instanciaSiebel.getTipoPlano().equals("C"))
			query = QueryWarehouse.getQuery("erro440siebelAtualizaDadosInterfaceCorporate");
		else
			query = QueryWarehouse.getQuery("erro440siebelAtualizaDadosInterfaceRetail");
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel.getRpon());

			pstmt.executeUpdate();
			
			pstmt.close();
						
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return;
	}

	
	public void retornaPlanoCSP(InstanciaSiebelVO instancia, String status) {
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaCsp");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				instancia.setCspSiebel(rs.getString("siebelcomponentdescription"));
				instancia.setCspIdSiebel(rs.getInt("arborcomponentid"));
				instancia.setCspIdKenan(xe.deParaCspSiebelKenan(rs.getInt("arborcomponentid")));
				instancia.setDataCspSiebel(rs.getDate("end_dt"));
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	public void retornaPlanoFranquia(InstanciaSiebelVO instancia) {
		
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440siebelRetornaFranquia");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getRpon());
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				instancia.setFranquiaSiebel(rs.getString("siebelcomponentdescription"));
				instancia.setFranquiaIdSiebel(rs.getInt("arborcomponentid"));
				instancia.setFranquiaIdKenan(xe.deParaCspSiebelKenan(rs.getInt("arborcomponentid")));
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	
	public boolean retornaDesignador(InstanciaSiebelVO instancia){
		
		boolean retorno = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = "";

			query = QueryWarehouse.getQuery("erro440siebelBuscaDesignador");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				if (retorno)
					retorno = false;
				instancia.setDesignador(rs.getString("asset_num"));
				retorno = true;
			}
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return retorno;
		
	}

	public void retornaRponDesignadorStatusAcao(InstanciaSiebelVO instancia, String status){

		instancia.setResultado(-2);
		instancia.setLog("Erro Siebel 5: Rpon ativo não encontrado");

		String tipoInstancia = "ExternalId";
		int tamanhoExternalId = 10;
		if (instancia.getInstancia().length() < tamanhoExternalId)
			tamanhoExternalId = instancia.getInstancia().length();

		if (instancia.getInstancia().startsWith("TV-")){
			tipoInstancia = "TV";
		}else if ((instancia.getInstancia().startsWith("TLF") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("VIV") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("CTB") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("TLM") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("TIM") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("TCS") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("SCT") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("EBT") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("BTC") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("OI9") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("CLA") && instancia.getInstancia().length() == 9) ||
				  (instancia.getInstancia().startsWith("INT") && instancia.getInstancia().length() == 9) ){
			tipoInstancia = "Cobilling";
		}else if ( ! isNumber(instancia.getInstancia().substring(0,tamanhoExternalId))){
			instancia.setLog("Siebel: External_id inválido");
			return;
		}else if (instancia.getInstancia().startsWith("800")){
			tipoInstancia = "0800";
		}

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			String query = "";
			PreparedStatement pstmt = null;
			
			if (tipoInstancia.equals("0800")){
				query = QueryWarehouse.getQuery("erro440siebelRetornaRponDesignador800");
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(2, status);
				
			}else if (tipoInstancia.equals("TV")){ 
				query = QueryWarehouse.getQuery("erro440siebelRetornaRponTV");
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, instancia.getInstancia());
				pstmt.setString(2, status);
				
			}else{
				query = QueryWarehouse.getQuery("erro440siebelRetornaRponDesignador");
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, status);
				pstmt.setString(2, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(3, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(4, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(5, status);
				pstmt.setString(6, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(7, status);
				pstmt.setString(8, instancia.getInstancia().substring(0,tamanhoExternalId));
				pstmt.setString(9, status);

			}
			
			ResultSet rs = pstmt.executeQuery();
			String porteDaPrimeira = null;
			
			if (rs.next()){
				instancia.setResultado(0);
				instancia.setLog("");
				instancia.setRpon(rs.getString("rpon"));
				instancia.setDesignador(rs.getString("designador"));
				instancia.setStatus(rs.getString("status"));
				instancia.setAcao(rs.getString("acao"));

				if (status == "Ativo"){
					porteDaPrimeira = rs.getString("porte");
					if (rs.next()){
						if(rs.getString("porte") == porteDaPrimeira){ // Faz isto pois o Retail não duplica instancia 
							instancia.setResultado(-2);
							instancia.setLog("Siebel: Rpon ativo duplicado no Siebel");
						}
					}
				}else{
					instancia.setResultado(-2);
					instancia.setLog("Siebel: Rpon encontrado com status " + instancia.getStatus());
				}

			}
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return;
		
	}

	// Check if given string is a number (digits only)
	public static boolean isNumber(String string) {
		return string.matches("^\\d+$");
	}

	
}
