select count(*) qtde_servicos from (
    select ce.external_id, ce.subscr_no, ce.active_date, ce.inactive_date, s.chg_dt, s.service_active_dt, s.service_inactive_dt, ce.view_status
    from customer_id_equip_map_view ce
    join service s on s.subscr_no = ce.subscr_no
                  and s.service_inactive_dt is null
    where ce.external_id = ?
    and ce.external_id_type = 6
    and ce.view_status = 2
    and not exists (select * from customer_id_equip_map where subscr_no = ce.subscr_no and inactive_date is null)
)