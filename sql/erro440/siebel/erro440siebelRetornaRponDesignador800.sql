SELECT 'C' porte,
	   S.ASSET_NUM designador,
       S.ROW_ID rpon,
       i.stat_cd status,
       s.action_cd acao
  FROM S_PARAM P,  
       S_QUOTE_ITEM I, 
       S_QUOTE_SOLN S 
WHERE I.QUOTE_SOLN_ID = S.ROW_ID 
AND   P.REF_TABLE_ID = I.ROW_ID 
AND   P.X_TEXTO_LIVRE IS NOT NULL    
AND   ? = x_texto_livre    
and   i.prod_id != '1-1BPLSY'  -- RETIRADA FIGURACAO NA LISTA
AND   COPIED_FLG = 'N'  
AND   STAT_CD = ?
ORDER BY S.CREATED DESC
