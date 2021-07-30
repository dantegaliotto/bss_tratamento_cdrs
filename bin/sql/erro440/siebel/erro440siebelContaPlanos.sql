select count(1) contagem from (
/*
select distinct plano name, row_id, eff_end_dt
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
           and s_quote_item.stat_cd = 'Ativo')
 where plano in (select siebelcomponentdescription
                   from mapeamentodecomponentes
                  where arborcomponentid in (select component_id
                                               from cdr_plano_local)
                    and arborcomponentid <> 23233
                    )
union */
SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd  = 'Ativo'
AND elemento.prod_id = produto.row_id
and (produto.name           like 'Plano Degrau%'
     or produto.name           like 'Plano Corporate%'
     or produto.name           like 'Plano MIX%'
     or produto.name           like 'Plano Bronze%'
     or produto.name           like 'Plano Prata%'
     or produto.name           like 'Plano Ouro%'
     or produto.name           like 'Econômico 30000%'
     or produto.name           like 'Econômico 6000%'
  )
)