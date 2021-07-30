select count(1) nivellavoisier from (
select distinct c.external_id_type
  from customer_id_equip_map c
 where subscr_no in (
             select subscr_no 
               from service 
              where parent_account_no in (
                        select parent_account_no from service
                        where subscr_no in (
                                  select subscr_no 
                                  from customer_id_equip_map 
                                  where external_id = ?
                                  and inactive_date is null
                                  )
                     )
                and service_inactive_dt is null    
          )
      and inactive_date is null
      and external_id_type in (6,7,10)
      )
