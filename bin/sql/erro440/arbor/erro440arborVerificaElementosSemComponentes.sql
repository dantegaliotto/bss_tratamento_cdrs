/* QUERY NOVA QUE PEGA POR QQ TIPO DE FALTA DE RELAÇÃO  
 * PRODUCT - CMF_COMPONENT_ELEMENT - CMF_PACKAGE_COMPONENT */
select * from product p
where parent_subscr_no = ?
and billing_inactive_dt is null
and exists (select 1 from rate_usage where element_id = p.element_id)
and not exists (
select * from cmf_component_element cce, cmf_package_component cpc
where cce.association_id = p.tracking_id
and cce.component_inst_id = cpc.component_inst_id
and cpc.inactive_dt is null
AND cce.association_type  = 1)

/* QUERY ANTIGA QUE SÓ PEGAVA PELA DATA
SELECT distinct cpc.component_id component_id, cce.component_inst_id, cce.association_id
FROM product p,
  product_elements pe,
  cmf_component_element cce,
  cmf_package_component cpc
WHERE p.parent_subscr_no  = ? AND 
    p.element_id          = pe.element_id
AND p.tracking_id         = cce.association_id
AND cce.association_type  = 1
AND cce.component_inst_id = cpc.component_inst_id
and cpc.inactive_dt is not null
--and cpc.inactive_dt is null
and p.product_inactive_dt is null
and (exists (select 1 from rate_usage where element_id = p.element_id)
     or
    exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id))
*/