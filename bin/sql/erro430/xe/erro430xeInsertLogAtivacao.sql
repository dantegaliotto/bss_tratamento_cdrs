INSERT
INTO BSS_430_ATIVACOES
  (
 	processo        , created        , external_id   , external_id_type , subscrno       , 
 	package_id      , component_id   , dt_ativacao   , server_id        , dt_envio 	     , 
 	flag            , motivo         , mod_lavoisier , jurisdiction     , units          , 
 	created_real
  )
  VALUES
  (
    'Erro430'       , trunc(sysdate) , ?             , ?                , ?              , 
    ?               , ?              , ?             , ?                , trunc(sysdate) , 
    ?               , ?              , ?             , ?                , ?              , 
    trunc(sysdate)
  )

  
