SELECT external_id,
       primeiro_cdr,
       ultimo_cdr,
       total_cdrs
  FROM CDRS_WORK_aux
  where subscr_no = ?
  --and rownum < 3