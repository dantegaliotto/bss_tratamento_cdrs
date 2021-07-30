-- NOVA QUERY DE BUSCA DE PLANOS RETAIL ENVIADA POR MARCONATTO - 27/03/2012
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
           and s_quote_item.stat_cd = 'Ativo')
 where plano in (select siebelcomponentdescription
                   from mapeamentodecomponentes
                  where arborcomponentid in (29398,29399,29742,29744,29741,
                                             29397,29743,29395))
 order by criacao desc

/*
SELECT elemento.row_id, produto.NAME, elemento.eff_end_dt
 FROM s_quote_item elemento, s_prod_int produto
WHERE elemento.quote_soln_id = ?
  AND elemento.stat_cd = ?
  AND (   (elemento.prod_id IN (SELECT row_id
                                  FROM s_prod_int
                                 WHERE NAME IN (SELECT nome_siebel
                                                  FROM cdr_plano_local)
                                                               or name in ('Plano Smart Maxx Zero Celular Local',
'Plano Smart Maxx Promo',
'Plano Smart Maxx Promo Celular Local',
'Plano Smart Light Celular Local - Plano Uso',
'Plano Smart Promo Celular Local - Plano Uso',
'Plano Smart Light',
'Plano Smart Celular Local',
'Plano Smart Maxx Zero',
'Plano Smart Maxx Light Celular Local',
'Plano WebLine',
'Plano Smart',
'Plano Smart Light Celular Local',
'Plano Smart Celular Local - Plano Uso',
'Plano GVT 300',
'Plano Unique 300')                               
                                                  ))
                                                )
  AND elemento.prod_id = produto.row_id
*/



/* query anterior (antes alteração marconatto 2011-12-29
SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd  = ?
AND ( elemento.prod_id IN (SELECT row_id FROM bq_tp_siebel_local)
      or elemento.prod_id = '1-148'      -- BASICO LOCAL 
      or elemento.prod_id = '1-30VGRU')  -- TRANSMIT
AND elemento.prod_id = produto.row_id
*/