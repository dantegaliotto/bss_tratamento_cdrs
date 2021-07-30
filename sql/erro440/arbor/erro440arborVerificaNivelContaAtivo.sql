SELECT *
FROM product p,
  product_elements pe,
  cmf_component_element cce,
  cmf_package_component cpc
WHERE p.parent_account_no  = ?
and cpc.component_id = ?
-- and cpc.active_dt <= ?
AND p.element_id          = pe.element_id
AND p.tracking_id         = cce.association_id
AND cce.association_type  = 0
AND cce.component_inst_id = cpc.component_inst_id
and cpc.inactive_dt is null
and p.product_inactive_dt is null