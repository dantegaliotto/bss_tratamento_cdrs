update BSS_ATIVACOES_AUTOROT 
set result = ?
where processo = 'Erro440'
and external_id = ?
and COMPONENT_ID = ?
and trunc(DT_ENVIO) =  trunc(sysdate)
and (result is null)
     
