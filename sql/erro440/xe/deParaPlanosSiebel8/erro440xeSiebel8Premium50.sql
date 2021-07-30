-- UPDATE PARA 'PREMIUM 50'
update bss_tratamento_cdrs
   set   componentid = 26699,
          csp_siebel = null, 
         cspid_kenan = null,
    franquia_n_conta = 'Franquia Premium 50',
    comp_id_franquia = 27899,
         csp_n_conta = null,                        
   comp_id_csp_conta = 0,
     data_csp_siebel = data_plano_siebel
where created = trunc(sysdate)
and plano = 'Premium 50'
