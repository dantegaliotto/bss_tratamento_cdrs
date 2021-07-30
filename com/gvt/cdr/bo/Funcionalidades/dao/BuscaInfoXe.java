package com.gvt.cdr.bo.Funcionalidades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Funcionalidades.vo.CdrVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia152Vox800RIUFVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;

public class BuscaInfoXe{

	private static BuscaInfoXe instance;

	public BuscaInfoXe(){

	}
	
	public BuscaInfoXe getInstance() {
		if (instance == null)
			instance = new BuscaInfoXe();

		return instance;
		
	}

	
	public void updatePidsDuplicados() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdatePidsDuplicados");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updateInstanciaIrma() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateInstanciaIrma");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updateCatarinaREQ1634425() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateCatarinaREQ1634425");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}
	
	public void updateSemGuiador() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateTemGuiador");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updatePortfolio() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdatePortfolio");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	
	public void updateFavoritosColOk(){
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateFavoritosColOk");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updateFavoritosComandoPBCAT(){
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateFavoritosComandoPBCAT");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}


	public void updateNaoEncontradosNaPgen(){
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateNaoEncontradosNaPgen");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}


	
	public void updateFavoritosColErro() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateFavoritosColErro");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updateFavoritosColOutros() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateFavoritosColOutros");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	
	public void updateLorenFavoritos() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateLorenFavoritos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	public void updateInativoNoKenan() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeUpdateInativoNoKenan");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	}

	
	public List<Instancia440RetailVO> carregaListaInstanciasAnalise() {

		List<Instancia440RetailVO> listaInstancias = new ArrayList<Instancia440RetailVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			Instancia440RetailVO reg = null;

			String query = QueryWarehouse.getQuery("XeBuscaInfoCarregaListaInstanciasAnalise");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new Instancia440RetailVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setTipoUso(rs.getInt("tu1"));
				reg.setPrimeiroCdr(rs.getDate("cdrini"));
			
				listaInstancias.add(reg);

				if (contador % 100 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaInstancias;

	}

	public List<Instancia440RetailVO> carregaListaInstanciasAnaliseFavoritos() {

		List<Instancia440RetailVO> listaInstancias = new ArrayList<Instancia440RetailVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			Instancia440RetailVO reg = null;

			String query = QueryWarehouse.getQuery("XeBuscaInfoCarregaListaInstanciasAnaliseFavoritos");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new Instancia440RetailVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setTipoUso(rs.getInt("tu1"));
				reg.setPrimeiroCdr(rs.getDate("cdrini"));
			
				listaInstancias.add(reg);

				if (contador % 100 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaInstancias;

	}

	public List<CdrVO> carregaListaCDRsTratamentoFavoritos() {

		List<CdrVO> listaCdrs = new ArrayList<CdrVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			CdrVO reg = null;

			String query = QueryWarehouse.getQuery("XeBuscaInfoCarregaListaCDRsTrataFavoritos");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new CdrVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setExternalTrackingId(rs.getString("ext_tracking_id"));
			
				listaCdrs.add(reg);

				if (contador % 100 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaCdrs;

	}

	public List<Instancia152Vox800RIUFVO> carregaListaVOX800RiUF() {

		List<Instancia152Vox800RIUFVO> listaInstancias = new ArrayList<Instancia152Vox800RIUFVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			Instancia152Vox800RIUFVO reg = null;

			String query = QueryWarehouse.getQuery("XeBuscaInstancia152Vox800RIUFVO");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new Instancia152Vox800RIUFVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setValor(rs.getDouble("valor"));
			
				listaInstancias.add(reg);

				if (contador % 100 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaInstancias;

	}


	
	public List<Instancia440RetailVO> carregaListaInstancias440PidDuplicado() {

		List<Instancia440RetailVO> listaInstancias = new ArrayList<Instancia440RetailVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			Instancia440RetailVO reg = null;

			String query = QueryWarehouse.getQuery("carregaListaInstancias440PidDuplicado");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new Instancia440RetailVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setPrimeiroCdr(rs.getDate("primeirocdr"));
				reg.setUltimoCdr(rs.getDate("ultimocdr"));
				reg.setValor(rs.getDouble("valor"));
				reg.setTipoUso(rs.getInt("tipo_uso"));
				
				listaInstancias.add(reg);

				if (contador % 100 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaInstancias;

	}

	
	public void insereInfo440Retail(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeInsereInfo440Retail");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1,  instancia.getConta());
			pstmt.setInt    (2,  instancia.getServerId());
			pstmt.setDate   (3,  instancia.getDtGuiador());
			pstmt.setString (4,  instancia.getIrmaComGuiador());
			pstmt.setString (5,  instancia.getPlano());
			pstmt.setDate   (6,  instancia.getDtGuiadorInativo1());
			pstmt.setDate   (7,  instancia.getDtGuiadorInativo2());
			pstmt.setString (8,  instancia.getOrdemAtivacao());
			pstmt.setString (9,  instancia.getOrdemInativacao());
			pstmt.setString (10, instancia.getExternalId());

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
		
	}

	public void insereInfo440Favorito(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeInsereInfo440Favorito");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1, instancia.getStatusFavorito());
			pstmt.setDate   (2, instancia.getDataFavorito());
			pstmt.setString (3, instancia.getExternalId());

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
		
	}

	public void insereInfo440CDRFavorito(CdrVO cdr){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeInsereInfo440CDRFavorito");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt    (1, cdr.getTipoUsoCorreto());
			pstmt.setString (2, cdr.getExternalTrackingId());

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
		
	}

	public void insereComandosCorrecaoVox800RIUF(Instancia152Vox800RIUFVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("XeInsereCorrecao152UFVox800RI");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1, instancia.getExternalId());
			pstmt.setDouble (2, instancia.getValor());
			pstmt.setString (3, instancia.getExternalIdCorreto());
			pstmt.setString (4, instancia.getComandoCorrecao());
			

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
		
	}

	
	
}


