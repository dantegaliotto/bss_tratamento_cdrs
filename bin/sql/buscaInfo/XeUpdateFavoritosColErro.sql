update bss_440_retail
set cenario_analise = 'Loren Favoritos - Erro COL', acao_it = 'Corrigir tipo_uso dos CDRs - Corrigir instância no COL'
where cenario_analise like 'Loren Favoritos%'
and status_favorito = 'Ativado'
--and acao_it is null