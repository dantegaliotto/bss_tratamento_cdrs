-- UPDATE PARA 'MAX GVT 10000'
update bss_tratamento_cdrs
   set   componentid = 29509,
          csp_siebel = 'Dial Tone LDN Ligue Local - LDI Básico', 
         cspid_kenan = 29520,
    franquia_n_conta = 'Max GVT 10000 - Franquia Mensal',         
    comp_id_franquia = 29513,
         csp_n_conta = 'Ligue Local 500',                        
   comp_id_csp_conta = 29515,
     data_csp_siebel = data_plano_siebel
where created = trunc(sysdate)
and plano = 'Max GVT 10000'
