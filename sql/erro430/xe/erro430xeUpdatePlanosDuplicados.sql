--- desativacoes com plano duplicado - so um update simples

update bss_tratamento_cdrs_produtos
   set desconectar = 9
 where componentinst in 
--	SELECT bp.component_id,btc.parcomponentidlocal,btc.parcomponentidvc,btc.parcomponentidldn,btc.parcomponentidldi,
--	instr(btc.parcomponentidlocal||' '||btc.parcomponentidvc||' '||btc.parcomponentidldn||' '||btc.parcomponentidldi, bp.component_id) resultado, 
--	btc.parcomponentidlocal||' '||btc.parcomponentidvc||' '||btc.parcomponentidldn||' '||btc.parcomponentidldi
--- calculo de desativações para plano duplicado
(	  select distinct b1.componentinst componentinst 
		from bss_tratamento_cdrs_produtos b1,
       -- pega quem tem mais de um mesmo component_id respondendo pela mesma msg_id
       -- seleciona um dos componentist para ser o que vai ficar, os diferentes vao 
       -- desconectar
         (select msg_id, component_id, max(componentinst) componentinst, count(*) 
            from bss_tratamento_cdrs_produtos
	       where processo = 'Erro430' and created = trunc(sysdate)
           and dtinactive is null
	       group by msg_id, component_id
	      having count(*) > 1) b2
		where b1.msg_id = b2.msg_id and b1.created = trunc(sysdate)
		  and b1.component_id = b2.component_id
		  and b1.componentinst <> b2.componentinst
) and created = trunc(sysdate)
