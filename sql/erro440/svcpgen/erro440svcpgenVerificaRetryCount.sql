select * 
from cdrs_work
where external_id = ?
and retry_count = 10
and miu_disp_status <> 0
and miu_disp_code in (0,1)
