SELECT distinct p.element_id, p.component_id, p.billing_active_dt
FROM product p,
  product_elements pe
WHERE p.parent_subscr_no  = ? AND 
    p.element_id          = pe.element_id
and p.billing_inactive_dt is null
