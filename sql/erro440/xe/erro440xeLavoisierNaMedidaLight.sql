update bss_tratamento_cdrs
set csp_siebel = 'GVT na Medida Light', 
    componentid = 30370,
    cspid_siebel = 30488,
    cspid_kenan = 30488
where created = trunc(sysdate)
and upper(plano) = 'GVT NA MEDIDA LIGHT'