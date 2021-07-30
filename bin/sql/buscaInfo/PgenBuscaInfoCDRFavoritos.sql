select min(uso_de) uso_original
from DQ_OWNER.GVT_ENRIQUECEDOR_FAV_ALT
where tracking_id = 'GV1'||?
