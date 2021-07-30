select row_id rpon, asset_num designador, action_cd acao, status_cd status, 'R' porte
from s_quote_soln
where asset_num = ?
and status_cd = ?
and   prod_id != '1-1BPLSY'  -- RETIRADA FIGURACAO NA LISTA
and   copied_flg = 'N'  
