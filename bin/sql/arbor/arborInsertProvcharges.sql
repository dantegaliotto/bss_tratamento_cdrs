INSERT
INTO gvt_prov_charges
  (
    element_id,
    extid_acctno,
    ext_id_type,
    package_id,
    component_id,
    start_date,
    flag,
    run_status
  )
  VALUES
  (
    ?,
    ?,
    ?,
    (select package_id
          from package_components
         where component_id = ?),
    ?,
    ?,
    ?,
    ?
  )