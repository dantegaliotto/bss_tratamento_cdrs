update bss_tratamento_cdrs_range 
set result = ? 
where processo = 'Erro440'
and instancia = ?
and external_id = ?
and created = trunc(sysdate)