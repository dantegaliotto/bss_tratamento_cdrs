SELECT cpc.component_id component_id,  cpc.active_dt dt_ativacao
FROM product p,
  product_elements pe,
  usage_type_groups utg,
  cmf_component_element cce,
  cmf_package_component cpc
WHERE p.parent_subscr_no  = ?
and cpc.component_id = ?
and p.element_id          = pe.element_id
AND utg.type_group_usg    = pe.type_group_usg
AND p.tracking_id         = cce.association_id
AND cce.association_type  = 1
AND cce.component_inst_id = cpc.component_inst_id
and cpc.inactive_dt is null
and p.product_inactive_dt is null
