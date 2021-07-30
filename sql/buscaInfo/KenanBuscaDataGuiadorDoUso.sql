select p.billing_active_dt data_guiador
  from customer_id_equip_map c
  join product p               on p.parent_subscr_no = c.subscr_no 
                              and p.billing_inactive_dt is null
  join rate_usage ru           on ru.element_id = p.element_id 
                              and (ru.type_id_usg = ?
                                  or ru.type_id_usg in (select type_id_usg_out from USAGE_TYPES_SPLIT 
                                                         where type_id_usg_in = ?
                                                           and element_id = p.element_id)) 
                              and ru.inactive_dt is null
where c.external_id = ?
  and c.inactive_date is null
  and c.external_id_type = 6
