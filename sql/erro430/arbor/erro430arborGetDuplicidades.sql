select distinct ext_id.ext_id          external_id,
                p1.tracking_id         tr_id1, 
                p1.element_id          el_id1, 
                p1.component_id        comp_id1, 
                cce1.package_id        pack_id1,
                cce1.component_inst_id component_inst_id1,
                p1.billing_active_dt   act_dt1, 
                p1.billing_inactive_dt inac_dt1,
                p2.tracking_id         tr_id2, 
                p2.element_id          el_id2, 
                p2.component_id        comp_id2, 
                cce2.package_id        pack_id2,
                cce2.component_inst_id component_inst_id2,
                p2.billing_active_dt   act_dt2, 
                p2.billing_inactive_dt inac_dt2,
                ciem.external_id_type  ext_id_type
from (select ?   ext_id      from dual) ext_id,
     (select ?   data_inicio from dual) data_inicio,
     product p1, usage_type_groups u1, product_elements pe1, cmf_component_element cce1,
     product p2, usage_type_groups u2, product_elements pe2, cmf_component_element cce2,
     customer_id_equip_map ciem
where p1.parent_subscr_no in (select distinct subscr_no from customer_id_equip_map
                               where external_id = ext_id.ext_id
                                 and inactive_date is null)
and p1.parent_subscr_no = p2.parent_subscr_no
and (p1.billing_active_dt <> p1.billing_inactive_dt or p1.billing_inactive_dt is null)
and (p2.billing_active_dt <> p2.billing_inactive_dt or p2.billing_inactive_dt is null)
--and p1.tracking_id < p2.tracking_id
and (p1.billing_active_dt between p2.billing_active_dt and nvl(p2.billing_inactive_dt,'31/12/2080')-1 or
     nvl(p1.billing_inactive_dt,'29/12/2080')-1 between p2.billing_active_dt and nvl(p2.billing_inactive_dt,'31/12/2080')-1)
     and p1.element_id = pe1.element_id and pe1.type_group_usg = u1.type_group_usg
     and p2.element_id = pe2.element_id and pe2.type_group_usg = u2.type_group_usg
     and cce1.association_id = p1.tracking_id and cce1.component_id = p1.component_id 
     and cce2.association_id = p2.tracking_id and cce2.component_id = p2.component_id 
     and u1.type_id_usg = u2.type_id_usg
     and (p1.billing_inactive_dt >= data_inicio.data_inicio or p1.billing_inactive_dt is null)
     and (p2.billing_inactive_dt >= data_inicio.data_inicio or p2.billing_inactive_dt is null)
     and p1.parent_subscr_no = ciem.subscr_no
     and ciem.inactive_date is null
      and p1.billing_inactive_dt is null 
     -- alterar aqui para pegar tbm duplicidades do tipo 2 ativos
     and p2.billing_inactive_dt is not null
     -- no caso com duas datas inativas o primeiro tem que ter uma data de ativacao menor
--     and (p1.billing_active_dt < p2.billing_active_dt 
--          or (p1.billing_active_dt = p2.billing_active_dt and p1.tracking_id < p2.tracking_id))
     and ciem.external_id_type <> 1
     and p1.tracking_id <> p2.tracking_id
    order by p1.element_id


/*  abaixo para pegar qdo tem 1 ativo e outro inativo
select distinct ext_id.ext_id          external_id,
                p1.tracking_id         tr_id1, 
                p1.element_id          el_id1, 
                p1.component_id        comp_id1, 
                cce1.package_id        pack_id1,
                cce1.component_inst_id component_inst_id1,
                p1.billing_active_dt   act_dt1, 
                p1.billing_inactive_dt inac_dt1,
                p2.tracking_id         tr_id2, 
                p2.element_id          el_id2, 
                p2.component_id        comp_id2, 
                cce2.package_id        pack_id2,
                cce2.component_inst_id component_inst_id2,
                p2.billing_active_dt   act_dt2, 
                p2.billing_inactive_dt inac_dt2,
                ciem.external_id_type  ext_id_type
from (select ?    ext_id      from dual) ext_id,
     (select ?    data_inicio from dual) data_inicio,
     product p1, usage_type_groups u1, product_elements pe1, cmf_component_element cce1,
     product p2, usage_type_groups u2, product_elements pe2, cmf_component_element cce2,
     customer_id_equip_map ciem
where p1.parent_subscr_no in (select distinct subscr_no from customer_id_equip_map
                               where external_id = ext_id.ext_id
                                 and inactive_date is null)
and p1.parent_subscr_no = p2.parent_subscr_no
and (p1.billing_active_dt <> p1.billing_inactive_dt or p1.billing_inactive_dt is null)
and (p2.billing_active_dt <> p2.billing_inactive_dt or p2.billing_inactive_dt is null)
--and p1.tracking_id < p2.tracking_id
and (p1.billing_active_dt between p2.billing_active_dt and nvl(p2.billing_inactive_dt,'31/12/2080')-1 or
     nvl(p1.billing_inactive_dt,'29/12/2080')-1 between p2.billing_active_dt and nvl(p2.billing_inactive_dt,'31/12/2080')-1)
     and p1.element_id = pe1.element_id and pe1.type_group_usg = u1.type_group_usg
     and p2.element_id = pe2.element_id and pe2.type_group_usg = u2.type_group_usg
     and cce1.association_id = p1.tracking_id and cce1.component_id = p1.component_id 
     and cce2.association_id = p2.tracking_id and cce2.component_id = p2.component_id 
     and u1.type_id_usg = u2.type_id_usg
     and (p1.billing_inactive_dt >= data_inicio.data_inicio or p1.billing_inactive_dt is null)
     and (p2.billing_inactive_dt >= data_inicio.data_inicio or p2.billing_inactive_dt is null)
     and p1.parent_subscr_no = ciem.subscr_no
     and ciem.inactive_date is null
--     and p1.billing_inactive_dt is not null -- alterar para pegar tbm duplicidades do tipo 2 inativos 
     and p2.billing_inactive_dt is null
     and ciem.external_id_type <> 1
    order by p1.element_id
*/