/* usada como base = buscar csp
SELECT siebelcomponentdescription, arborcomponentid
FROM MAPEAMENTODECOMPONENTES
WHERE SIEBELCOMPONENTDESCRIPTION in
(
SELECT SPI.NAME
FROM S_QUOTE_SOLN SQS,
     S_QUOTE_ITEM SQI,
     S_PROD_INT SPI
WHERE SQS.INV_ACCNT_ID = SQI.INV_ACCNT_ID
AND QUOTE_SOLN_ID IS NULL
AND SQS.ASSET_NUM in (?, ?)
AND SQI.PROD_ID = SPI.ROW_ID
AND SPI.PROD_CD = 'Plano CSP 25'
and sqi.stat_cd = 'Ativo'
) */

-- buscar franquia
SELECT siebelcomponentdescription, arborcomponentid
FROM MAPEAMENTODECOMPONENTES
WHERE SIEBELCOMPONENTDESCRIPTION in
(
SELECT --elemento.row_id,
  produto.name --,
  --elemento.eff_end_dt,
  --elemento.stat_cd
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.inv_accnt_id = 
           (select inv_accnt_id from s_quote_soln where row_id = ?)
--AND elemento.stat_cd  = 'Ativo'
AND elemento.prod_id IN
  (SELECT row_id FROM bq_tp_siebel_franquia
  )
AND elemento.prod_id = produto.row_id
)