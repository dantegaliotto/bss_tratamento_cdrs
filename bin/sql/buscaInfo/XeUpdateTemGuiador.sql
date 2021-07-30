update bss_440_retail
set cenario_guiador = (case when data_guiador is null then 'Sem guiador' else 'Guiador Ok' end)
where cenario_guiador is null
