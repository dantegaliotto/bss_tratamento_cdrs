select external_id, 
       primeirocdr,
       data_plano_siebel
  from bss_tratamento_cdrs b
 where resultado = 1
                           -- retirar
                           --and rownum < 100
   and processo='Erro440'
   and created = trunc(sysdate)
   and primeirocdr < data_plano_siebel
   

 
 

