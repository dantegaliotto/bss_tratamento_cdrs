INSERT
INTO bss_tratamento_cdrs_load (
   processo    ,   seq         ,  created     ,  annotation  ,
   external_id ,   designador  ,  rpon        ,  primeiro_cdr,  
   ultimo_cdr  ,   total_cdrs  ,  log         ,  resultado   ,  status  ,
   qtd440      ,   qtd430      ,  flg_siebel8 ,  flg_nobill  ,  created_real,
   valor
  )
  VALUES (
   'Erro440',     ?,         trunc(sysdate),     ?,
   ?,             ?,         ?,                  ?,            
   ?,             ?,         ?,                  ?,             ?,
   ?,             ?,         ?,                  ?,             trunc(sysdate),
   trunc(?,2)
)
  