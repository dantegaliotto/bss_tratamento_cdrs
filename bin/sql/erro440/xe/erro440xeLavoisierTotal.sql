-- UPDATE PARA 'GVT Ilimitado Total Casa'
update bss_tratamento_cdrs
set csp_siebel = '25 Cidade Local', 
    vc = 'GVT Ilimitado Total Casa - Franquia Mensal',
    componentid = 30369,
    cspid_siebel = 30490,
    cspid_kenan = 30490,
    parcomponentidvc = 30363
where created = trunc(sysdate)
and upper(plano) = 'GVT ILIMITADO TOTAL CASA'
