update bss_440_retail
set cenario_analise = 'Loren Favoritos - Ok COL', acao_it = 'Corrigir tipo_uso dos CDRs'
where cenario_analise like 'Loren Favoritos%'
and (status_favorito = 'Desconectado' or status_favorito is null)
--and data_favorito >= trunc(cdrfim)