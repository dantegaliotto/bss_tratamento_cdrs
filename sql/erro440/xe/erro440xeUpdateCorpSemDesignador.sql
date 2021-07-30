update bss_tratamento_cdrs
	set 
	log = 'Erro Siebel: Plano corporate em instancia sem designador',
	resultado = -2
	where created = trunc(sysdate)
	and designador is null
	and processo = 'Erro440'
	and plano like '%orpo%' 
