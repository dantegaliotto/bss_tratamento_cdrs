/*
select external_id, 
       designador, 
       qtde, 
       valor, 
       primeirocdr, 
       ultimocdr
 from bss_tratamento152_corp
*/

select external_id      external_id, 
       quote_designador designador, 
       count(*)         qtde, 
       sum(valor)       valor, 
       min(trans_dt)    primeirocdr, 
       max(trans_dt)    ultimocdr
  from cdrs_work
 where tipo_erro = 152
   and quote_designador in ('CTA-30WLG7BO-16069','GNA-301KA53PN-16069','PUI-301M24U7B-16069','FNS-301M26ZLD-16069','UMR-301LZLBIY-16069','CTA-30WLG9PG-16069','CSL-106CAFZB-031',
'SPO-301LI9RXJ-16077','SPO-301L9SD8R-16069','LDA-301M2AHDK-031','BCU-301M2ETTS-9592','CTA-301LIOD5F-16069')  
   and cenario = 'Não existe no Kenan - CORP'
--   and cenario = 'Nao existe designador COPIED_FLG = N no Siebel 5'
 group by external_id, quote_designador
-- RETIRAR
 having max(trans_dt) >= '01/03/2019'
