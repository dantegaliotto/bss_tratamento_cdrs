select max(t1.billing_active_dt) dt_desativar, 
       t1.external_id       external_id, 
       max(t1.component_inst_id) component_inst_id, 
       t1.component_id      component_id, 
       t1.package_id        package_id, 
       t1.parent_subscr_no  subscr_no from (
select ciem.external_id, 
       cpc.component_id, 
       p.element_id,
       cce.component_inst_id,
       cce.package_id,
       p.billing_active_dt,
       p.parent_subscr_no
         from customer_id_equip_map ciem,
              product p,
              cmf_component_element cce,
              cmf_package_component cpc
where
    ciem.external_id = ?
    and ciem.inactive_date is null
    and ciem.external_id_type = 1
    and ciem.subscr_no = p.parent_subscr_no
    and p.billing_inactive_dt is null
    and p.tracking_id = cce.association_id
    and cce.component_inst_id = cpc.component_inst_id
    and cpc.parent_subscr_no = ciem.subscr_no
    ) t1, (
select ciem.external_id, 
       cpc.component_id, 
       p.element_id, 
       count(*)
         from customer_id_equip_map ciem,
              product p,
              cmf_component_element cce,
              cmf_package_component cpc
where
    ciem.external_id = ?
    and ciem.inactive_date is null
    and ciem.external_id_type = 1
    and ciem.subscr_no = p.parent_subscr_no
    and p.billing_inactive_dt is null
    and p.tracking_id = cce.association_id
    and cce.component_inst_id = cpc.component_inst_id
    and cpc.parent_subscr_no = ciem.subscr_no
    and (exists (select 1 from rate_usage where element_id = p.element_id)
     or  exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id))    
    
    group by ciem.external_id, cpc.component_id, p.element_id
    having count(*) > 1
    ) t2
    where t1.component_id = t2.component_id
--    order by t1.billing_active_dt 
    group by 
       t1.external_id, 
       t1.component_id, 
       t1.package_id, 
       t1.parent_subscr_no


/*
select t1.billing_active_dt dt_desativar, 
       t1.external_id       external_id, 
       t1.component_inst_id component_inst_id, 
       t1.component_id      component_id, 
       t1.package_id        package_id, 
       t1.parent_subscr_no  subscr_no from (
select ciem.external_id, 
       cpc.component_id, 
       p.element_id,
       cce.component_inst_id,
       cce.package_id,
       p.billing_active_dt,
       p.parent_subscr_no
         from customer_id_equip_map ciem,
              product p,
              cmf_component_element cce,
              cmf_package_component cpc
where
    ciem.external_id = ?
    and ciem.inactive_date is null
    and ciem.external_id_type = 1
    and ciem.subscr_no = p.parent_subscr_no
    and p.billing_inactive_dt is null
    and p.tracking_id = cce.association_id
    and cce.component_inst_id = cpc.component_inst_id
    and cpc.parent_subscr_no = ciem.subscr_no
    ) t1, (
select ciem.external_id, 
       cpc.component_id, 
       p.element_id, 
       count(*)
         from customer_id_equip_map ciem,
              product p,
              cmf_component_element cce,
              cmf_package_component cpc
where
    ciem.external_id = ?
    and ciem.inactive_date is null
    and ciem.external_id_type = 1
    and ciem.subscr_no = p.parent_subscr_no
    and p.billing_inactive_dt is null
    and p.tracking_id = cce.association_id
    and cce.component_inst_id = cpc.component_inst_id
    and cpc.parent_subscr_no = ciem.subscr_no
    and (exists (select 1 from rate_usage where element_id = p.element_id)
     or  exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id))    
    
    group by ciem.external_id, cpc.component_id, p.element_id
    having count(*) > 1
    ) t2
    where t1.component_id = t2.component_id
    order by t1.billing_active_dt 
*/