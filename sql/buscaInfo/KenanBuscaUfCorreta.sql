select external_id 
from external_id_equip_map
where external_id like ? || '%'
and length(external_id) = 12
and external_id_type = 1
and inactive_date is null

