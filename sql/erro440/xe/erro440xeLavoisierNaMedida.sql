-- UPDATE PARA 'GVT - Na Medida Casa'
update bss_tratamento_cdrs
set csp_siebel = 'GVT Na Medida Nacional', 
    vc = 'GVT na Medida Casa - Franquia Mensal',
    componentid = 30367,
    cspid_siebel = 30488,
    cspid_kenan = 30488,
    parcomponentidvc = 30361
where created = trunc(sysdate)
and upper(plano) = 'GVT NA MEDIDA CASA'
