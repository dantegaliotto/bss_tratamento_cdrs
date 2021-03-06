SELECT CPC.PACKAGE_ID package_id, 
       CPC.COMPONENT_INST_ID COMPONENT_INST_ID
FROM 
    CUSTOMER_ID_EQUIP_MAP EIEM,
    CMF_PACKAGE_COMPONENT CPC
WHERE EIEM.SUBSCR_NO = CPC.PARENT_SUBSCR_NO
AND   EIEM.EXTERNAL_ID_TYPE = 6
AND   EIEM.INACTIVE_DATE IS NULL
AND   CPC.COMPONENT_ID IN (?)  -- component_id do plano
AND   EIEM.external_id  = ?    -- external_id
AND   CPC.INACTIVE_DT IS NULL
