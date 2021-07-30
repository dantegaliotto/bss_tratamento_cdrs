update bss_tratamento_cdrs
set csp_siebel = 'Dial Tone LDN Ligue Local - LDI Básico (forçado pelo sistema)',
cspid_siebel = '24059',
cspid_kenan = '24059',
resultado = 6
where created = trunc(sysdate)
and plano = 'Assinatura Mensal - Plano Economix Flex 800'
and (csp_siebel in ('CSP 25 - Para Plano Pacote', 'CSP 25 - Ligue Local 300')
  or csp_siebel is null)