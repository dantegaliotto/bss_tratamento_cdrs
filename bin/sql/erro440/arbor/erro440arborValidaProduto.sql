SELECT 1 valida
FROM cmf_package_component cpc
WHERE cpc.parent_subscr_no = ?
AND cpc.COMPONENT_ID      = ?
AND cpc.inactive_dt      IS NULL