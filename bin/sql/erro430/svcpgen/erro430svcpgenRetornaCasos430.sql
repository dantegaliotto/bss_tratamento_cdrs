-- EXTRACAO PELA SVCPGEN CDRS_WORK
SELECT external_id, 
       min(msg_id_serv)               msg_id_serv,
       min(trans_dt)                  primeiro_cdr, 
       max(trans_dt)                  ultimo_cdr, 
       COUNT(1)                       qtde
  FROM cdrs_work
 WHERE tipo_erro = 430

       --and external_id in (select external_id from external_id)
and quote_designador = 'SOC-301KNXFW5-9592-301KNXFW7'
 
 group by external_id
 order by count(1) desc
