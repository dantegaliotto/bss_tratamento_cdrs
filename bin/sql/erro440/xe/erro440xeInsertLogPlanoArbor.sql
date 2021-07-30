insert
into bss_tratamento_cdrs_plano (
processo           , seq                       , created                   , instancia           , external_id           , 
serverid           , subscrno                  , componentid               , dtplano             , component_inst_id     , 
package_id         , arborcomponentdescription , tipo_plano                , validacao_elementos , nivelkenan            , 
created_real       )                                                                        
     values (                                                                                                              
'Erro440'          , ?                         , trunc(sysdate)            , ?                  , ?                      ,
?                  , ?                         , ?                         , ?                  , ?                      ,
?                  , ?                         , ?                         , ?                  , ?                      , 
trunc(sysdate)     )