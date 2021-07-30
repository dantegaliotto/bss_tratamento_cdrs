INSERT INTO gvt_prov_charges
(
 ELEMENT_ID, 
 EXTID_ACCTNO, 
 EXT_ID_TYPE, 
 PACKAGE_ID, 
 COMPONENT_ID,
 START_DATE, 
 FLAG, 
 RUN_STATUS,
 jurisdiction,
 units,
 units_type
 ) values (
      ' ',
-- ? , -- row_id do elemento (comentado e substituido por 'espaco'
      TRIM(?), -- as EXTID_ACCTNO, 
      ?, -- as ex_id_type
      ?, -- as PACKAGE_ID
      ?, -- as COMPONENT_ID 
      ?,
      ?,
      '99',
      ?,
      ?,
      ?
)

