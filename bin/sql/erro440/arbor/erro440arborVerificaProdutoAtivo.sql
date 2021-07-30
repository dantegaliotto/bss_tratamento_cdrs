select distinct type_id_usg 
from rate_usage ru, package_component_members pcm
where ru.element_id = pcm.member_id
and ru.inactive_dt is null
and pcm.component_id = ?
and type_id_usg = ?
