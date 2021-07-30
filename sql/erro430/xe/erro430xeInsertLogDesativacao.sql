INSERT
INTO bss_430_desativacoes
  (
    processo                  , created       , external_id       ,
    subscrno                  , dt_desativar  , package_id       , COMPONENT_INST_ID ,
	component_id              , server_id     , dt_envio         , motivo            ,
	external_id_type , created_real
  )
  VALUES
  (
    'Erro430'                 , trunc(sysdate), ?                 , 
    ?                         , ?             , ?                , ?                 ,
    ?                         , ?             , trunc(sysdate)   , ?                 ,
    ?                         , trunc(sysdate)
  ) 

  
