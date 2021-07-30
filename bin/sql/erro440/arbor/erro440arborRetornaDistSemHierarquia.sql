select 'NÃO' hierarq, ca.account_no, ca.external_id,
       nvl(to_char(p.component_id),'S/ PROD. CM'), 
       pro.override_rate/100 dist_atual -- não exibe a dist_atual
       --nvl(to_char(bi.bill_ref_no),'S/ FATURA COM CONSUMO MIN.'), bi.prep_date, 
       --bid.subtype_code, 
       --bid.amount/100 dist_fatura, (bid.amount+bid.discount)/100
from customer_id_acct_map ca 
left join product p on p.parent_account_no = ca.account_no
                   and p.component_id = 25977
                   and p.billing_inactive_dt is null
--left join bill_invoice bi on bi.account_no = p.parent_account_no
--                   and bi.process_num = 5
--                   and bi.prep_date >= trunc(sysdate)-180
--left join bill_invoice_detail bid on bid.bill_ref_no = bi.bill_ref_no
--                   and bid.subtype_code = p.element_id
left join product_rate_override pro
                    on pro.tracking_id = p.tracking_id
                   and pro.tracking_id_serv = p.tracking_id_serv
                   and pro.inactive_dt is null
where ca.account_no = 7080355
  and ca.external_id_type = 1
--order by bi.bill_ref_no desc
;