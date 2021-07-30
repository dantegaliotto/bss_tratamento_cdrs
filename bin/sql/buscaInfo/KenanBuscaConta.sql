select ca.external_id conta 
from customer_id_equip_map ce
join service s on s.subscr_no = ce.subscr_no and s.service_inactive_dt is null
join customer_id_acct_map ca on ca.account_no = s.parent_account_no and ca.external_id_type = 1 and ca.inactive_date is null
where ce.external_id = ? and ce.inactive_date is null and ce.external_id_type = 6
