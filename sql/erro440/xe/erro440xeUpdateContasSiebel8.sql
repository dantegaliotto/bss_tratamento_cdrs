update bss_tratamento_cdrs
	set log = 'Erro Siebel: Instancia Siebel 8',resultado = -2
	where created = trunc(sysdate)
	and processo = 'Erro440'
	and extid_acctno like '89%'
