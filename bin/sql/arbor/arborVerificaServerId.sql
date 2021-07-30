select 1 from ARBOR.customer_id_equip_map
where external_id = ?
and inactive_date is null
--and external_id_type in (6, 10)
