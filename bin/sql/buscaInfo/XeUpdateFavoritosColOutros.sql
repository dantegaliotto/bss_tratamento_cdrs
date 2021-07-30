update bss_440_retail
set cenario_analise = (case when cdrfim >= trunc(sysdate)-30 then 'Loren Favoritos - Erro COL' else 'Loren Favoritos - CDRs antigos' end)
--, 
--    acao_it = (case when cdrfim >= trunc(sysdate)-30 then 'Corrigir tipo_uso dos CDRs - Corrigir instância no COL' else 'Corrigir tipo_uso dos CDRs' end)
where cenario_analise like 'Loren Favoritos%'
and acao_it is null