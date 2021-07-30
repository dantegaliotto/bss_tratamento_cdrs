select * from (
select prod.*,
       row_number() OVER(PARTITION BY external_id ORDER BY dt_inativacao desc, dt_ativacao desc) RN 
       from (
      select distinct c.external_id, 
                      c.subscr_no,
                      p.tracking_id,
                      cce.component_inst_id, 
                      p.element_id,
                      p.billing_active_dt   dt_ativacao,
                      p.billing_inactive_dt dt_inativacao, 
                      ooA.order_number      ordem_ativ,
                      ooI.order_number      ordem_inat
        from customer_id_equip_map c
        join product p             on p.parent_subscr_no = c.subscr_no
                                  and p.billing_inactive_dt <= ?
                                  and p.billing_inactive_dt is not null
        join rate_usage r          on r.element_id = p.element_id 
                                  and r.type_id_usg = ?
                                  and r.inactive_dt is null
        join cmf_component_element cce on cce.association_id = p.tracking_id 
                                      and cce.component_id = p.component_id 
                                      and cce.active_dt = p.billing_active_dt
        join ord_service_order osoA on osoA.subscr_no = c.subscr_no
        join ord_item oiA on oiA.service_order_id = osoA.service_order_id
                        and oiA.member_id = p.element_id
                        and oiA.member_inst_id = p.tracking_id
                        and oiA.item_action_id = 10
        join ord_order ooA on ooA.order_id = osoA.order_id
        join ord_service_order osoI on osoI.subscr_no = c.subscr_no
        join ord_item oiI on oiI.service_order_id = osoI.service_order_id
                        and oiI.member_id = p.element_id
                        and oiI.member_inst_id = p.tracking_id
                        and oiI.item_action_id = 30
        join ord_order ooI on ooI.order_id = osoI.order_id
       where c.external_id = ?
         and c.external_id_type = 1
         and c.inactive_date is null
   ) prod ) where RN = 1
