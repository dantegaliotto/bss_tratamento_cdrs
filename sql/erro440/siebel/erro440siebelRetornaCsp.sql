SELECT siebelcomponentdescription, arborcomponentid, sqs.end_dt end_dt 
FROM S_QUOTE_SOLN SQS,
     S_QUOTE_ITEM SQI,
     S_PROD_INT SPI,
     MAPEAMENTODECOMPONENTES m
WHERE SQS.INV_ACCNT_ID = SQI.INV_ACCNT_ID
AND QUOTE_SOLN_ID IS NULL
--AND SQS.ASSET_NUM in (?,?)
and sqs.row_id = ?
AND SQI.PROD_ID = SPI.ROW_ID
AND SPI.PROD_CD = 'Plano CSP 25'
and sqi.stat_cd = ?
and m.siebelcomponentdescription = spi.name
and rownum = 1
