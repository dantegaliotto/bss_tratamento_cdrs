update bss_440_retail
set cenario_analise = 'Loren Favoritos'
where portfolio = 'LOREN'
and (tu1 in (643,271,412) or tu2 in (643,271,412))
and cenario_analise is null
