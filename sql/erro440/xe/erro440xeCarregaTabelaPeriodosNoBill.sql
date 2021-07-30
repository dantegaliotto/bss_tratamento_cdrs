INSERT INTO periodos_no_bill (
   select trunc(sysdate), pe.server_id, pe.quando, min(ps.quando), pe.account_no
     from
     (select account_no, quando, server_id from trocas_no_bill
       where created = trunc(sysdate)
         and new_no_bill = 1) pe,
     (select account_no, quando from trocas_no_bill
       where created = trunc(sysdate)
         and old_no_bill = 1) ps
   where pe.account_no = ps.account_no
   and (pe.quando <= ps.quando)
group by pe.account_no, pe.server_id, pe.quando)
