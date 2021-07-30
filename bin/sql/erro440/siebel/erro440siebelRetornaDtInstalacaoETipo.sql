SELECT a.end_dt, p.name
FROM s_quote_soln a, s_prod_int p 
WHERE a.row_id = ?
and a.serv_prod_id = p.row_id
