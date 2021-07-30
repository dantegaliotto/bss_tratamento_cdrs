update bss_440_retail
set portfolio = (case when plano in ('Ilimitado Fixo e Móvel Local - SP','Ilimitado Fixo Local','Ilimitado Fixo e Móvel Brasil','Ilimitado Fixo e Móvel Local','Vivo Fixo Simples',
                                'Ilimitado Fixo e Móvel Brasil - SP','Ilimitado Fixo Brasil e Móvel Local - SP','Ilimitado Fixo Local - SP','Ilimitado Fixo Brasil e Móvel Local',
                                'Vivo Banda Larga Avulsa','Vivo TV Avulsa DTH','Plano Básico - FSP','Vivo Fixo Simples - SP',
                                'Fixo Ilimitado Nacional Empresas - FSP','Fixo e Móvel Ilimitado Local Empresas - FSP',
                                'Fixo e Móvel Ilimitado Local Empresas - SP','Fixo Ilimitado Local Empresas - FSP',
                                'Fixo e Móvel Ilimitado Nacional Empresas - FSP')
                                 then 'LOREN' else NULL end)
where portfolio is null
