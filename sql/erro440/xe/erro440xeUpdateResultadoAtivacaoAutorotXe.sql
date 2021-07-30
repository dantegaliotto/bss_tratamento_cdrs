update bss_ativacoes_autorot 
set result = ? || '<' || to_char(sysdate, 'dd-mm-yyyy hh24:mi') || '>' 
where flg_enviado_autorot = 1
and COMPONENT_ID = ?
and external_id = ?
