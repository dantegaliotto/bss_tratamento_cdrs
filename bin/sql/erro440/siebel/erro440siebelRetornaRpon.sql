SELECT *
FROM s_quote_soln 
WHERE asset_num = ?
and status_cd = 'Ativo'
and copied_flg = 'N'
order by created desc