select package_id, component_inst_id 
from cmf_component_element
where association_id = ?
and component_id = ?
and rownum < 2
