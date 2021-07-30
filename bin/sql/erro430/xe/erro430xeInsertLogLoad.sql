INSERT
INTO bss_430_instancias (
   PROCESSO            , CREATED             , EXTERNAL_ID          , ACCOUNT_NO           , 
   SUBSCR_NO           , SERVERID            , CICLO                , LAVOISIER            , 
   PRIMEIROCDR         , ULTIMOCDR           , QTDE                 , DATA_INSTANCIA_KENAN , 
   LOG                 )
  VALUES (
   'Erro430'           , trunc(sysdate)      , ?                    , ?                    ,
   ?                   , ?                   , ?                    , ?                    ,
   ?                   , ?                   , ?                    , null                 , 
   null                )
  