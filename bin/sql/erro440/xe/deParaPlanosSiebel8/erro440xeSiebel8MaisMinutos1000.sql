-- UPDATE PARA 'MAIS MINUTOS 1000'
update bss_tratamento_cdrs
   set   componentid = 28480,
          csp_siebel = 'Dial Tone LDN Básico - LDI Básico',
         cspid_kenan = 25012,
    franquia_n_conta = 'Plano Básico - 5 minutos',
    comp_id_franquia = 25011,
         csp_n_conta = null,                        
   comp_id_csp_conta = 0,
     data_csp_siebel = data_plano_siebel
where created = trunc(sysdate)
and plano = 'Mais Minutos 1000'

