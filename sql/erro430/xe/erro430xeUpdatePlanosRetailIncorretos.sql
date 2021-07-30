--- desativações do R com plano diferente do siebel
update bss_tratamento_cdrs_produtos
   set desconectar = 7
  where processo = 'Erro430'
   and created = trunc(sysdate)
   and componentinst in
     (select componentinst
        from bss_tratamento_cdrs btc, bss_tratamento_cdrs_produtos bp
       where btc.processo = 'Erro430' and btc.created = trunc(sysdate) 
         and bp.processo = 'Erro430' and bp.created = trunc(sysdate)
         and btc.tipoplano = 'R' and bp.desconectar = 0 and btc.resultado = 0
         and nvl(btc.componentid, 0) > 0
         and btc.componentid <> bp.component_id
         and btc.cspid_kenan <> bp.component_id
         and btc.msg_id = bp.msg_id
         and bp.dtinactive is null 
      )
