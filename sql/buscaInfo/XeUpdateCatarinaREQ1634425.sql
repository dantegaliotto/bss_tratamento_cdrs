update bss_440_retail
  set cenario_analise = 'CATARINA - REQ1634425'
where cenario_analise is null
  and plano in ('Fixo Ilimitado Local Empresas - FSP','Fixo Ilimitado Local Empresas - SP')
  and tu1 like '6%' and tu2 like '6%'
