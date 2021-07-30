package com.gvt.cdr.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.gvt.cdr.bo.Erro152.Erro152CorpLoad;
import com.gvt.cdr.bo.Erro152.Erro152Validation;
import com.gvt.cdr.bo.Erro440.Erro440Load;
import com.gvt.cdr.bo.Erro440.Erro440Validation;
import com.gvt.cdr.bo.Erro440.Erro440Action;
import com.gvt.cdr.bo.Erro430.Erro430Load;
import com.gvt.cdr.bo.Erro430.Erro430Duplicidades;
import com.gvt.cdr.bo.Erro430.Erro430Action;
import com.gvt.cdr.bo.Funcionalidades.BuscaInfo440Retail;
import com.gvt.cdr.bo.Funcionalidades.BuscaInfo440RetailFavoritos;
import com.gvt.cdr.bo.Funcionalidades.Trata152Vox800RIEstadoDivergente;
import com.gvt.cdr.bo.Funcionalidades.Trata440RetailCDRsFavoritos;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;

public class TratamentoCDRsApp {

    public static final int FINALIZADO_OK = 0;
    public static final int FATAL_ERROR = -1;

    public static void main(String[] args) {

		long tInicio = System.currentTimeMillis();
		long tFinal;
		long tTotal;

		configApp();
		loadQueries();
				
		try{

			String processo   = "";
	
			try{
				processo   = args[0];
				
				if (processo.compareTo("Load440") == 0){
					Erro440Load c = new Erro440Load();
					c.executeLoad(args[1], args[2]);
				}else if (processo.compareTo("Validation440") == 0){
					Erro440Validation c = new Erro440Validation();
					c.executeValidation(args[1]);
				}else if (processo.compareTo("Action440") == 0){
					Erro440Action c = new Erro440Action();
					c.executeAction(args[1]);
					
				}else if (processo.compareTo("Load430") == 0){
					Erro430Load c = new Erro430Load();
					c.executeLoad430();
				}else if (processo.compareTo("Duplicidades430") == 0){
					Erro430Duplicidades c = new Erro430Duplicidades();
					c.executeDuplicidades430();
				}else if (processo.compareTo("Action430") == 0){
					Erro430Action c = new Erro430Action();
					c.executeAction430();

				}else if (processo.compareTo("BuscaInfo440Retail") == 0){
					BuscaInfo440Retail c = new BuscaInfo440Retail();
					c.executeBusca();
				}else if (processo.compareTo("Favoritos") == 0){
					BuscaInfo440RetailFavoritos c = new BuscaInfo440RetailFavoritos();
					c.executeBusca();
				}else if (processo.compareTo("TrataFavoritos") == 0){
					Trata440RetailCDRsFavoritos c = new Trata440RetailCDRsFavoritos();
					c.executeBusca();

				}else if (processo.compareTo("Load152Corp") == 0){
						Erro152CorpLoad c = new Erro152CorpLoad();
						c.executeLoad(args[1]);
				}else if (processo.compareTo("Validation152Corp") == 0){
					Erro152Validation c = new Erro152Validation();
					c.executeValidation();
					
				}else if (processo.compareTo("Vox800RIEstadoDivergente") == 0){
					Trata152Vox800RIEstadoDivergente c = new Trata152Vox800RIEstadoDivergente();
					c.executeBusca();
					
				}else{
					System.out.println("Opção " + args[0] + " não válida");
				}

			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("-------- ERRO DE SINTAXE --------");
				System.out.println("-------- Utilize:");
				System.out.println("-------- Load440       [s8|s5]      [Comentario]");
				System.out.println("-------- Validation440 [sequencial] [Comentario]");
				System.out.println("-------- Action440     [sequencial] [Comentario]");
			}


		}catch(Exception ex){
			
			Log.error("Falha genérica", ex);
			System.exit(FATAL_ERROR);
			
		}
		
		tFinal = System.currentTimeMillis();
		tTotal = (tFinal - tInicio) / 1000;
		if(tTotal<60)
			Log.info("Tempo total de execucao: " + tTotal + " segundos.");
		else
			Log.info("Tempo total de execucao: " + tTotal/60 + " minutos.");

		System.exit(FINALIZADO_OK);

	}
		
    private static void configApp(){
		
        Properties buildProp = new Properties();
        Properties configProp = new Properties();

        try {
            buildProp.load(new FileInputStream("build.properties"));
            configProp.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            Log.error("Nao foi possível ler o arquivo build.properties", e);
        }

        Log.init(buildProp.getProperty("build.logger"), configProp.getProperty("log.erros"));

        Log.info("GVT - IT Systems Support");
        Log.info(buildProp.getProperty("build.title"));
        Log.info("Versao " + buildProp.getProperty("build.version"));
		
	}
	
	private static void loadQueries(){
		
		try{
			Log.info("Carregando Queries");
			Class.forName(QueryWarehouse.class.getName());
		}
		catch (ClassNotFoundException ex){
			Log.error("Não encontrada classe QueryWarehouse", ex);
			System.exit(TratamentoCDRsApp.FATAL_ERROR);
		}
	}
	
}
