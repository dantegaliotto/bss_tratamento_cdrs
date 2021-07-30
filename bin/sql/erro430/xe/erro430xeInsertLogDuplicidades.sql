INSERT
INTO BSS_430_DUPLICIDADES (
   processo         , created           , external_id        , serverid           , 
   external_id_type , subscr_no         , mod_lavoisier      , tracking_id1       , 
   element_id1      , component_id1     , package_id1        , component_inst_id1 , 
   active_dt1       , inactive_dt1      , tracking_id2       , element_id2        , 
   component_id2    , package_id2       , component_inst_id2 , active_dt2         , 
   inactive_dt2     )
VALUES (
   'Erro430'        , trunc(sysdate)    , ?                  , ?                  , 
   ?                , ?                 , ?                  , ?                  ,  
   ?                , ?                 , ?                  , ?                  , 
   ?                , ?                 , ?                  , ?                  ,
   ?                , ?                 , ?                  , ?                  ,
   ?                )
