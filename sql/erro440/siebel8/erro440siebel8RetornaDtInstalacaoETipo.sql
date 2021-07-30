select * from (
select sa.x_install_dt, sp.name, sa.status_cd
from siebel.s_asset sa,
     siebel.s_prod_int sp
where sa.serial_num = ?
  and sa.prod_id = sp.row_id
 -- and sa.status_cd = 'Ativo'
 order by sa.status_cd
 ) where rownum = 1 -- faz isso para poder pegar o plano caso a instancia esteja inativa, mas dá preferência para as instâncias ativas

/* query siebel 8 que só traz as instancias ativas
select sa.x_install_dt, sp.name
from siebel.s_asset sa,
     siebel.s_prod_int sp
where sa.serial_num = ?
  and sa.prod_id = sp.row_id
  and sa.status_cd = 'Ativo'
*/  
/* query do siebel 5
SELECT a.end_dt, p.name
FROM s_quote_soln a, s_prod_int p 
WHERE a.row_id = ?
and a.serv_prod_id = p.row_id
*/