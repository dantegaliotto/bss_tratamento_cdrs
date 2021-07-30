INSERT
INTO bss_tratamento_cdrs (
processo                , seq                    , created             , annotation             , external_id          ,
designador              , rpon                   , account_no          , extid_acctno           , serverid             ,
ciclo                   , jurisdiction           , primeirocdr         , ultimocdr              , qtde                 ,
data_instancia_kenan    , data_instancia_siebel  , data_plano_siebel   , tipoplano              , plano                ,
csp_siebel              , local                  , vc                  , ldn                    , ldi                  ,
componentid             , cspid_siebel           , cspid_kenan         , parcomponentidlocal    , parcomponentidvc     ,
parcomponentidldn       , parcomponentidldi      , lavoisier           , franquia_siebel        , franquiaid_siebel    ,
franquiaid_kenan        , franquia_n_conta       , comp_id_franquia    , csp_n_conta            , comp_id_csp_conta    ,
resultado               , resultado_inicial      , log                 , tipo_instancia_siebel  , rowidelementoplano   ,
data_ultimo_faturamento , data_csp_siebel        , qtd440              , qtd430                 , created_real         ,
log_view                , valor                  , account_nob         , parent_id              , hierarchy_id
  ) VALUES (
'Erro440'               , ?                      , trunc(sysdate)      , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , 0                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , ?                    ,
?                       , ?                      , ?                   , ?                      , trunc(sysdate)       ,
?                       , ?                      , ?                   , ?                      , ?                    
  )

