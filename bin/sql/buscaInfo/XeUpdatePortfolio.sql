update bss_440_retail
set portfolio = (case when plano in ('Ilimitado Fixo e M�vel Local - SP','Ilimitado Fixo Local','Ilimitado Fixo e M�vel Brasil','Ilimitado Fixo e M�vel Local','Vivo Fixo Simples',
                                'Ilimitado Fixo e M�vel Brasil - SP','Ilimitado Fixo Brasil e M�vel Local - SP','Ilimitado Fixo Local - SP','Ilimitado Fixo Brasil e M�vel Local',
                                'Vivo Banda Larga Avulsa','Vivo TV Avulsa DTH','Plano B�sico - FSP','Vivo Fixo Simples - SP',
                                'Fixo Ilimitado Nacional Empresas - FSP','Fixo e M�vel Ilimitado Local Empresas - FSP',
                                'Fixo e M�vel Ilimitado Local Empresas - SP','Fixo Ilimitado Local Empresas - FSP',
                                'Fixo e M�vel Ilimitado Nacional Empresas - FSP')
                                 then 'LOREN' else NULL end)
where portfolio is null
