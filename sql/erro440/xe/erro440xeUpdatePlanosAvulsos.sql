update bss_tratamento_cdrs
   set log       = 'Incorrigíveis - Plano Avulso',
       resultado = -2
 where created   = trunc(sysdate)
   and processo  = 'Erro440'
   and plano in ('Plano Valor Zero',
                 'Plano TV Avulsa',
                 'Plano Avulso - Valor Zero',
                 'Plano Valor Zero Smart')
