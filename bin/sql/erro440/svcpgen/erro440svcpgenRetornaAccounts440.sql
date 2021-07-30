select account_no, max(server_id) server_id
from cdrs_work cw
--join external_id ei on cw.external_id = ei.external_id 
--where rownum < 10000
where tipo_erro = 440
--and external_id = '6430500299'
group by account_no


/*
SELECT account_no, max(server_id) server_id
from cdrs_work
where tipo_erro = 440
group by account_no
*/