select external_id ext_id_account_no
  from external_id_acct_map
 where account_no = (select account_no from external_id_equip_map
                     where external_id = ?
                     and inactive_date is null
                     and external_id_type = 1
                     and rownum = 1)
   and external_id_type = 1

/*
select external_id ext_id_account_no from customer_id_acct_map 
where account_no =
( select distinct(parent_account_no) from product 
 where parent_subscr_no =
           ( select subscr_no from customer_id_equip_map 
             where external_id = ? --- exemplo de CT1
             and external_id_type = 1
             and inactive_date is null --- para pegar só a linha ativa
           )
)
and external_id_type = 1
*/