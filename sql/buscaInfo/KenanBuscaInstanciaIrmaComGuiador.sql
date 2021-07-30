select min(ce1.external_id) instancia_irma
from customer_id_equip_map ce
join service s on s.subscr_no = ce.subscr_no and s.service_inactive_dt is null
join service s1 on s1.parent_account_no = s.parent_account_no and s1.service_inactive_dt is null
join customer_id_equip_map ce1 on s1.subscr_no = ce1.subscr_no and ce1.inactive_date is null and ce1.external_id_type = 6
                              and ce1.external_id <> ce.external_id
join product p on p.parent_subscr_no = ce1.subscr_no and p.billing_inactive_dt is null --and p.element_id = 1607
join rate_usage ru           on ru.element_id = p.element_id 
                              and (ru.type_id_usg = ?
                                  or ru.type_id_usg in (select type_id_usg_out from USAGE_TYPES_SPLIT 
                                                         where type_id_usg_in = ?
                                                           and element_id = p.element_id)) 
                              and ru.inactive_dt is null
where ce.external_id_type = 6 and ce.inactive_date is null
and ce.external_id = ?