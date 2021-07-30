update bss_440_retail
set cenario_analise = 'Instância Irmã'
where cenario_guiador = 'Sem guiador'
and cenario_analise is null
and irma_guiador is not null
