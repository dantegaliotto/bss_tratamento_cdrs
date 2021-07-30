select account_no
from external_id_equip_map
where external_id = ?
and inactive_date is null
and external_id_type in (6, 10)