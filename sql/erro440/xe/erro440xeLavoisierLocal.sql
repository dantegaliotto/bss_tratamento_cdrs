-- UPDATE PARA 'GVT Ilimitado Local Casa'
update bss_tratamento_cdrs
set csp_siebel = '25 Cidade Local', 
    vc = 'GVT Ilimitado Local Casa - Franquia Mensal',
    componentid = 30368,
    cspid_siebel = 30490,
    cspid_kenan = 30490,
    parcomponentidvc = 30362
where created = trunc(sysdate)
and upper(plano) = 'GVT ILIMITADO LOCAL CASA'