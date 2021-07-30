SELECT SQS.ASSET_NUM             ASSET_NUM, 
       TO_CHAR(S2.X_ACCT_ID_NUM) CLIENTE, 
       TO_CHAR(S3.X_ACCT_ID_NUM) AGREGADORA, 
       TO_CHAR(S.X_ACCT_ID_NUM)  COBRANCA,
       --S2.X_ACCT_BILLABLE_STATUS CLI, 
       --S3.X_ACCT_BILLABLE_STATUS AGR, 
       --S.X_ACCT_BILLABLE_STATUS  COB, 
       --S2.OU_TYPE_CD             STATCLI, 
       --S3.OU_TYPE_CD             STATAGR,
       --S.OU_TYPE_CD              STATCOB, 
       S2X.ATTRIB_35             PORTECLI, 
       S2.ROW_ID                 ROWIDCLI, 
       S3.ROW_ID                 ROWIDAGG, 
       S.ROW_ID                  ROWIDCOB, 
       SQS.ROW_ID                ROWID_INSTANCIA--, 
       --SCA.X_ENDERECO_COMPLETO   AGR_END,
       --SCA1.X_ENDERECO_COMPLETO  COB_END, 
       --SIP.X_PR_BILL_ADDR_ID     PERF_COB, 
       --SAP.ROW_ID                ROW_ID_COB, 
       --SAP_CS.ROW_ID             ROW_ID_SERV, 
       --SAP.X_COUNTY_CODE         X_COUNTY_CODE_COB, 
       --SAP.X_MUNICIPIO           X_MUNICIPIO_COB, 
       --SAP_CS.X_COUNTY_CODE      X_COUNTY_CODE_SERV, 
       --SAP_CS.X_MUNICIPIO        X_MUNICIPIO_SERV,
       --SUBSTR (SCA_CS.X_ENDERECO_COMPLETO,
       --LENGTH (SCA_CS.X_ENDERECO_COMPLETO)-1,
       --LENGTH (SCA_CS.X_ENDERECO_COMPLETO)) ESTADO_INST,
       --sqs.START_DT
  FROM S_ORG_EXT S, S_ORG_EXT S2, S_ORG_EXT S3, S_QUOTE_SOLN SQS, S_CON_ADDR SCA, S_CON_ADDR SCA1,S_CON_ADDR SCA_CS, S_INV_PROF SIP,S_ADDR_PER SAP, S_ADDR_PER SAP_CS, S_ORG_EXT_X S2X
  WHERE S3.ROW_ID = SCA.ACCNT_ID (+)
  AND S.ROW_ID = SCA1.ACCNT_ID (+)
  AND SQS.INV_ACCNT_ID = S.ROW_ID
  AND S.ROW_ID = SIP.ACCNT_ID
  AND SQS.Asset_Num = ?
  AND S.MASTER_OU_ID = S2.ROW_ID  
  AND S.PAR_OU_ID = S3.ROW_ID
  AND SCA1.ADDR_PER_ID = SAP.ROW_ID
  AND SQS.SERV_ACCNT_ID = SCA_CS.ACCNT_ID
  AND SCA_CS.ADDR_PER_ID = SAP_CS.ROW_ID
  AND S2.ROW_ID = S2X.ROW_ID
  AND SQS.COPIED_FLG = 'N'