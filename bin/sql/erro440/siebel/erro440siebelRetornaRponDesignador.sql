SELECT 'C' porte,
  SOE_COB.X_ACCT_ID_NUM CONTA_COBRANCA,
  SOE_SERV.X_ACCT_ID_NUM CONTA_SERVICO,
  SQS.ROW_ID RPON,
  SQS.X_TIPO_SEL,
  SQS.ASSET_NUM DESIGNADOR,
  SQS.START_DT DATA_SOLICITACAO_IS_SIEBEL,
  max(SQS.END_DT) DATA_INSTALACAO_IS_SIEBEL,
  max(SQS.X_DATA_DESCONEXAO) DATA_DESCONEXAO_SIEBEL,
  SQS.ACTION_CD acao,
  SQS.STATUS_CD STATUS,
  SQS.COPIED_FLG
FROM S_PARAM SP
JOIN S_QTEITEM_PARAM SQP ON SQP.PARAM_ID = SP.ROW_ID
JOIN S_QUOTE_ITEM SQI ON SQI.ROW_ID = SQP.QUOTE_ITEM_ID
                     AND SQI.PROD_ID IN ('1-N69K','1-8MKLKD', '1-N69E') -- DDR VOX IP, SERVI�O VOX DIGITAL TRONCO, SERVI�O VOX DDR
JOIN S_QUOTE_SOLN SQS ON SQS.ROW_ID = SQI.QUOTE_SOLN_ID
                     AND SQS.COPIED_FLG = 'N'
                     AND SQS.STATUS_CD  in ( ? )   -- STATUS
JOIN S_ORG_EXT SOE_COB ON SOE_COB.ROW_ID = SQS.INV_ACCNT_ID
JOIN S_ORG_EXT SOE_SERV ON SOE_SERV.ROW_ID = SQS.SERV_ACCNT_ID
WHERE SP.PARAM_NAME_ID IN ('1-CTTF', '1-8MKK18', '1-N69I') -- Range de ramais, Range de ramais, Range de n�meros
  AND TO_NUMBER(REGEXP_SUBSTR(?,'[0-9]+')) BETWEEN TO_NUMBER(SUBSTR(REGEXP_SUBSTR(X_RANGE_INICIAL,'[0-9]+'),0,10)) AND TO_NUMBER(SUBSTR(REGEXP_SUBSTR(X_RANGE_FINAL,'[0-9]+'),0,10))  
group by SOE_COB.X_ACCT_ID_NUM, SOE_SERV.X_ACCT_ID_NUM, SQS.ROW_ID, SQS.X_TIPO_SEL, SQS.ASSET_NUM, SQS.START_DT, SQS.X_DATA_DESCONEXAO, SQS.ACTION_CD, SQS.STATUS_CD, SQS.COPIED_FLG
 
UNION ALL
 
-- RANGE CORPORATE VOX 3G
SELECT * FROM (
SELECT 'C' porte,
  SOE_COB.X_ACCT_ID_NUM CONTA_COBRANCA,
  SOE_SERV.X_ACCT_ID_NUM CONTA_SERVICO,
  SQS.ROW_ID RPON,
  SQS.X_TIPO_SEL,
  SQS.ASSET_NUM DESGINADOR,
  SQS.START_DT DATA_SOLICITACAO_IS_SIEBEL,
  max(SQS.END_DT) DATA_INSTALACAO_IS_SIEBEL,
  max(SQS.X_DATA_DESCONEXAO) DATA_DESCONEXAO_SIEBEL,
  SQS.ACTION_CD,
  SQS.STATUS_CD STATUS,
  SQS.COPIED_FLG
FROM S_QUOTE_ITEM SQI 
JOIN (
  SELECT SQP.QUOTE_ITEM_ID, SP.X_TEXTO_LIVRE
  FROM S_QTEITEM_PARAM SQP 
  JOIN S_PARAM SP ON SQP.PARAM_ID = SP.ROW_ID
  WHERE SP.PARAM_NAME_ID = '1-53IUUA'
) SP_RI ON SP_RI.QUOTE_ITEM_ID = SQI.ROW_ID
      AND TO_NUMBER(?) >= TO_NUMBER(REGEXP_SUBSTR(SP_RI.X_TEXTO_LIVRE,'[0-9]+'))       
JOIN (
  SELECT SQP.QUOTE_ITEM_ID, SP.X_TEXTO_LIVRE
  FROM S_QTEITEM_PARAM SQP 
  JOIN S_PARAM SP ON SQP.PARAM_ID = SP.ROW_ID
  WHERE SP.PARAM_NAME_ID = '1-53IUUB'
) SP_RF ON SP_RF.QUOTE_ITEM_ID = SQI.ROW_ID
       AND TO_NUMBER(?) <= TO_NUMBER(REGEXP_SUBSTR(SP_RF.X_TEXTO_LIVRE,'[0-9]+'))       
JOIN S_QUOTE_SOLN SQS ON SQS.ROW_ID = SQI.QUOTE_SOLN_ID                     
                     AND SQS.COPIED_FLG = 'N'
                     AND SQS.STATUS_CD in ( ? )    -- STATUS
JOIN S_ORG_EXT SOE_COB ON SOE_COB.ROW_ID = SQS.INV_ACCNT_ID
JOIN S_ORG_EXT SOE_SERV ON SOE_SERV.ROW_ID = SQS.SERV_ACCNT_ID
WHERE SQI.PROD_ID = '1-53IUPI'
group by SOE_COB.X_ACCT_ID_NUM, SOE_SERV.X_ACCT_ID_NUM, SQS.ROW_ID, SQS.X_TIPO_SEL, SQS.ASSET_NUM, SQS.START_DT, SQS.ACTION_CD, SQS.STATUS_CD, SQS.COPIED_FLG
  
  UNION ALL
 
-- RANGE CORPORATE 0800
 
SELECT 'C' porte, SOE_COB.X_ACCT_ID_NUM CONTA_COBRANCA,
  SOE_SERV.X_ACCT_ID_NUM CONTA_SERVICO,
  SQS.ROW_ID RPON,
  SQS.X_TIPO_SEL,
  SQS.ASSET_NUM DESGINADOR,
  SQS.START_DT DATA_SOLICITACAO_IS_SIEBEL,
  max(SQS.END_DT) DATA_INSTALACAO_IS_SIEBEL,
  max(nvl(SQS.X_DATA_DESCONEXAO,sysdate)) DATA_DESCONEXAO_SIEBEL,
  SQS.ACTION_CD,
  SQS.STATUS_CD STATUS,
  SQS.COPIED_FLG
FROM S_QUOTE_ITEM SQI
JOIN S_QTEITEM_PARAM SQP ON SQP.QUOTE_ITEM_ID = SQI.ROW_ID
JOIN S_PARAM SP ON SP.ROW_ID = SQP.PARAM_ID
                             AND SP.PARAM_NAME_ID IN ('1-18474R','1-2DLZ9I','3-IGHK0H', '3-V316LF')
               AND TO_NUMBER(?) = TO_NUMBER(REGEXP_SUBSTR(SP.X_TEXTO_LIVRE,'[0-9]+')) 
JOIN S_QUOTE_SOLN SQS ON SQS.ROW_ID = SQI.QUOTE_SOLN_ID
                     AND SQS.SERV_PROD_ID IN ('1-18473H','1-1884JY','3-IGGM8C','3-V32B08')
                     AND SQS.COPIED_FLG = 'N'
                     AND SQS.STATUS_CD in ( ? )    -- STATUS
JOIN S_ORG_EXT SOE_COB ON SOE_COB.ROW_ID = SQS.INV_ACCNT_ID
JOIN S_ORG_EXT SOE_SERV ON SOE_SERV.ROW_ID = SQS.SERV_ACCNT_ID
WHERE SQI.PROD_ID IN ('1-183311','1-1884J8','3-IGGM7R','3-V32B01', '3-IGGM7Q')
group by SOE_COB.X_ACCT_ID_NUM, SOE_SERV.X_ACCT_ID_NUM, SQS.ROW_ID, SQS.X_TIPO_SEL, SQS.ASSET_NUM, SQS.START_DT, SQS.ACTION_CD, SQS.STATUS_CD, SQS.COPIED_FLG
 
UNION ALL
 
-- NUMERO RETAIL
SELECT 'R' porte, SOE_COB.X_ACCT_ID_NUM CONTA_COBRANCA,
  SOE_SERV.X_ACCT_ID_NUM CONTA_SERVICO,
  SQS.ROW_ID RPON,
  SQS.X_TIPO_SEL,
  SQS.ASSET_NUM DESGINADOR,
  SQS.START_DT DATA_SOLICITACAO_IS_SIEBEL,
  max(SQS.END_DT) DATA_INSTALACAO_IS_SIEBEL,
  max(nvl(SQS.X_DATA_DESCONEXAO,sysdate)) DATA_DESCONEXAO_SIEBEL,
  SQS.ACTION_CD,
  SQS.STATUS_CD STATUS,
  SQS.COPIED_FLG
FROM S_QUOTE_SOLN SQS
JOIN S_ORG_EXT SOE_COB ON SOE_COB.ROW_ID = SQS.INV_ACCNT_ID
JOIN S_ORG_EXT SOE_SERV ON SOE_SERV.ROW_ID = SQS.SERV_ACCNT_ID
WHERE SQS.ASSET_NUM = ?
--  AND SQS.COPIED_FLG = 'N' -- n�o precisa considerar o copied_flag para o retail
  AND SQS.STATUS_CD in ( ? )    -- STATUS
group by SOE_COB.X_ACCT_ID_NUM, SOE_SERV.X_ACCT_ID_NUM, SQS.ROW_ID, SQS.X_TIPO_SEL, SQS.ASSET_NUM, SQS.START_DT, SQS.ACTION_CD, SQS.STATUS_CD, SQS.COPIED_FLG
 
) A
ORDER BY 1,6 DESC

