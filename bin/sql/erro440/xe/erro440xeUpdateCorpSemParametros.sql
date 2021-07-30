update bss_tratamento_cdrs
	set log = 'Siebel: Plano corporate em instancia sem parametros', resultado = -2
	where created = trunc(sysdate)
	and parcomponentidlocal = 0
	and processo = 'Erro440'
	and plano like '%orpo%' 
