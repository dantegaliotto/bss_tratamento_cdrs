select 
externalid,primeirocdr,ultimocdr,serverid,
tipo_uso,msg_id,subscr_no,plano,componentid
from bss_tratamento_cdrs
where created = trunc(sysdate)
and processo = 'Erro430'
and resultado >= 0

/*
select instancia externalid, 444 TIPO_USO, trunc(sysdate)-1000 primeirocdr, 
       trunc(sysdate) ultimocdr, 444 TOTAL_CDRS, 100 MSG_ID, 
       subscr_no, server_id server_id, 100 tipo_uso, 50 plano, 55555 componentid
from verifica_duplicidade
--where rownum < 5
*/

/*
select EXTERNAL_ID externalid, TIPO_USO, PRIMEIRO_CDR primeirocdr, 
       ULTIMO_CDR ultimocdr, TOTAL_CDRS, 100 MSG_ID, 
       subscr_no, sever_id server_id, 100 tipo_uso, 50 plano, 55555 componentid
from cdrs_work_aux
--where rownum < 5
*/