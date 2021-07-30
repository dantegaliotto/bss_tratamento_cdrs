select external_id, 
       min(trans_dt) primeiro_cdr, 
       max(trans_dt) ultimo_cdr, 
       count(*) qtde
  from cdrs_work
 where tipo_erro = 430
 ---
   --and external_id='4830379302'
 ---
 group by 'Erro430', trunc(sysdate), external_id, null
having count(*) >= 100

/*
SELECT cw.external_id    external_id,  
       cw.tipo_uso       tipo_uso,
       MIN(cw.trans_dt)  primeiro_cdr,
       MAX(cw.trans_dt)  ultimo_cdr,
       MAX(cw.msg_id) msg_id,
       COUNT(1)          total_cdrs
  FROM cdrs_work cw
 WHERE cw.tipo_erro = 430
                  -- excluir:
                  -- and cw.external_id = '1140857350'
                  -- and cw.external_id = '4430240829'
 GROUP BY cw.external_id,  
          cw.tipo_uso
                  -- excluir:
                  having count(1) > 10
  order by count(1) desc
*/