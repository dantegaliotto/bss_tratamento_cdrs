update bss_430_ativacoes 
   set result          = ?
 where created         = trunc(sysdate) 
   and external_id     = ?
   and component_id    = ?
   and trunc(dt_envio) =  trunc(sysdate)
   and result          is null
