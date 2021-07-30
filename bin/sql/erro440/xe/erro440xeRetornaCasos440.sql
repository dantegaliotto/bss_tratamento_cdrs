select rpon, 
       designador, 
       min(external_id) external_id, 
       min(primeiro_cdr) primeiro_cdr, 
       max(ultimo_cdr) ultimo_cdr, 
       sum(total_cdrs) qtde,
       sum(qtd440) qtd440,
       sum(qtd430) qtd430,
       sum(valor) valor
  from bss_tratamento_cdrs_load b
 where created = trunc(sysdate)
   and (resultado <> -2 or resultado is null)
   --and not exists(select * from bss_tratamento_cdrs where created = trunc(sysdate)
   --                  and rpon = b.rpon)
   and seq = ?
 group by rpon, designador
 order by sum(total_cdrs) desc