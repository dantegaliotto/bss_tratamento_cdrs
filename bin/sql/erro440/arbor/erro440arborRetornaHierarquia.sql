select cmf.account_no account_nob, cmf.parent_id, cmf.hierarchy_id
from customer_id_equip_map c 
join service s         on s.subscr_no = c.subscr_no
join cmf cmf           on cmf.account_no = s.parent_account_no
where c.external_id = ?
  and c.external_id_type = 1
  and c.inactive_date is null
