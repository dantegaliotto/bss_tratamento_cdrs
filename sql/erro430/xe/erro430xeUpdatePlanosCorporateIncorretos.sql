update bss_tratamento_cdrs_produtos
   set desconectar = 8
  where processo = 'Erro430'
   and created = trunc(sysdate)
   and componentinst in
  (select componentinst
     FROM bss_tratamento_cdrs btc, bss_tratamento_cdrs_produtos bp
    WHERE btc.processo = 'Erro430' and btc.created = trunc(sysdate)
      and bp.processo = 'Erro430' and bp.created = trunc(sysdate)
      and rpon is not null 
	  and tipoplano = 'C'
      and btc.msg_id = bp.msg_id
      and (( btc.parcomponentidldn > 0 and btc.tipo_uso like '2%' ) or
           ( btc.parcomponentidlocal > 0 and btc.tipo_uso like '4%' ) or
           ( btc.parcomponentidvc > 0 and btc.tipo_uso like '6%' ) or
           ( btc.parcomponentidldi > 0 and btc.tipo_uso not like '2%' and btc.tipo_uso not like '4%' and btc.tipo_uso not like '6%' ))       
      and instr(btc.parcomponentidlocal||' '||btc.parcomponentidvc||' '||btc.parcomponentidldn||' '||btc.parcomponentidldi, bp.component_id) = 0
   )