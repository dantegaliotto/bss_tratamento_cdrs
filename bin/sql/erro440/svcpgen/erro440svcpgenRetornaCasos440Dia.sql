SELECT external_id,
       min(cw.trans_dt), max(cw.trans_dt),
       COUNT(1) qtde
  FROM cdrs_work cw
 WHERE cw.tipo_erro in (440, 430)
 group by cw.external_id
 having count(1) > 1000
 order by count(1) desc
