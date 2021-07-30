select count(*) QTD_INSTANCIAS, 
       sum(qtde) QTD_CDRS
 from (
   select external_id instancias, 
          sum(qtde) qtde
     from bss_tratamento_cdrs b1, 
          (select * from bss_tratamento_cdrs_range
            where processo = 'Erro440' 
              and created = trunc(sysdate)) b2
    where b1.processo = 'Erro440' and b1.created = trunc(sysdate) 
      and b1.external_id = b2.instancia (+)
 group by b1.external_id

 )
