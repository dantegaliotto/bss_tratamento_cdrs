select sum(total_cdrs) qtde_incorrigiveis from bss_tratamento_cdrs_load_dia
where external_id = ?
and created = trunc(sysdate)
and dia < ?
