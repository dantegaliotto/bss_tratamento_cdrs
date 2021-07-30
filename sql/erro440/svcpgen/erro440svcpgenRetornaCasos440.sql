-- EXTRACAO PELA DGRC CDRS_WORK
SELECT external_id                    external_id , 
       quote_rpon                     rpon        ,
       quote_designador               designador  ,
       nvl(quote_status_cd,'Outros')  status      ,
       min(trans_dt)                  primeiro_cdr,
       max(trans_dt)                  ultimo_cdr  ,
       COUNT(1)                       qtde        ,
       sum(decode(tipo_erro,440,1,0)) qtd440      ,
       sum(decode(tipo_erro,430,1,0)) qtd430      ,
       max(cmf_no_bill)               no_bill     ,
       quote_flg_s8                   flg_siebel8 ,
       sum(valor)                     valor
  FROM cdrs_work
 WHERE tipo_erro in (440) --, 430, 532)
   -- NÃO PROCESSA TV POR CONTA DO VERSIONAMENTO
   and external_id not like 'TV-%'
   --and tipo_uso not in (235, 435)
   and nvl(quote_flg_s8,'N') = ?
   and no_bill_inad is null
   --and nvl(flg_siebel8,'N') = ?
--and external_id in ('6230896325','6230896326','6235264908')
--and external_id in (select external_id
--                      from bss_440_retail
--                     where cenario_guiador = 'Sem guiador'
--                     and plano not like 'Vivo%')
--and QUOTE_DESIGNADOR in ( select external_id from external_id )
--                      and external_id in ()
--and quote_designador in ('BSA-301K1KH8W-031','AJU-301DRXTD2-031','CHN-301LAW0WL-031','RJO-301L3XTC0-031','BRE-30KATKIW-9592')
--and cenario = 'Instância S5 com erro plano Kenan - CORP'
  and cenario = '0800 sem plano Kenan'
--and quote_designador like '%-%' and quote_designador not like 'TV%'
--and cenario = 'Instância S5 com erro plano Kenan - CORP'
group by external_id, quote_rpon, quote_designador, nvl(quote_status_cd,'Outros'), quote_flg_s8
order by count(1) desc

 
 