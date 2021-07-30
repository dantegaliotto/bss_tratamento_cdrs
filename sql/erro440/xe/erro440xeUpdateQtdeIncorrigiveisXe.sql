update bss_tratamento_cdrs_load 
set qtd_incorrigiveis = ?, qtd_corrigiveis = ?
where created = trunc(sysdate)
and external_id = ?
