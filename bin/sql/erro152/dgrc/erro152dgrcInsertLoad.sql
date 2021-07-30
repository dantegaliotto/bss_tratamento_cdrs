INSERT
INTO BSS_TRATAMENTO152_CORP (
    processo      , created       , annotation    , external_id  , designador   , 
    qtde          , valor         , primeirocdr   , ultimocdr    , contacli     , 
    contaagg      , contacob      , rowidcli      , rowidagg     , rowidcob     , 
    rowidins      , portecli      , vercli        , veragg       , vercob       , 
    verinstancia  , resultado     , log           )
VALUES (
    'Erro152'     , trunc(sysdate), ?             , ?            , ?            ,
	?             , ?             , ?             , ?            , ?            ,
	?             , ?             , ?             , ?            , ?            ,
	?             , ?             , null          , null         , null         ,
	null          , ?             , ?             )
