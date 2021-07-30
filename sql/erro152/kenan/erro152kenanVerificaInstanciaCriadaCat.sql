select server_id
from external_id_equip_map
where external_id = ?
and inactive_date is null
and external_id_type = 1
 