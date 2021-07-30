select plano name, row_id, eff_end_dt
  from (select s_prod_int.name plano,
               s_quote_item.eff_end_dt,
               s_quote_item.shipped_dt desconexao,
               s_quote_item.created criacao,
               s_quote_item.stat_cd status,
               s_quote_item.row_id row_id
          from s_quote_item,
               s_prod_int
         where s_quote_item.quote_soln_id = ?
           and s_quote_item.prod_id = s_prod_int.row_id
           and s_quote_item.stat_cd = ?)
 where plano in (select siebelcomponentdescription
                   from mapeamentodecomponentes
                  where arborcomponentid in (select component_id
                                               from cdr_plano_vc where nome_siebel like '%Celular%'))                   
 order by criacao desc
