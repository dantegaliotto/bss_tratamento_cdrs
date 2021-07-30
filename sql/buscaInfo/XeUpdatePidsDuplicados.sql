update bss_440_retail
set cenario_analise = 'PIDs duplicados'
where ordem_ativ = ordem_inat
and cenario_analise is null