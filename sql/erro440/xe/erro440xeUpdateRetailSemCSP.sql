update bss_tratamento_cdrs
set log = 'Siebel: Plano retail sem CSP no Siebel (n�o Premium)', resultado = -2
	where created = trunc(sysdate)
	and cspid_kenan = 0
	and processo = 'Erro440'
	and tipoplano = 'R'
  and plano not like '%remium%'
