select count(*) contagem from ( 

select distinct component_id,  
                dt_ativacao, 
                component_inst_id,
                package_id,
                min(arborcomponentdescription)      arborcomponentdescription
from (  -- NA BUCHA, COLOCA AQUI A QUERY QUE TRAZ OS PLANOS CONTA QUE O CARA TEM QUE É USADA NO LOAD

select distinct cpc.component_id             component_id,  
                p.billing_active_dt          dt_ativacao, 
                cpc.component_inst_id        component_inst_id,
                cpc.package_id               package_id,
                d.description_text           arborcomponentdescription,
                'Franquia'                   tipo_plano,
                'Conta'                      nivel_kenan
  from product                   p,
       cmf_component_element     cce,
       cmf_package_component     cpc,
       descriptions              d
 where p.parent_account_no = ?
   and p.billing_inactive_dt is null
   and p.tracking_id         = cce.association_id
   and cce.association_type  = 0
   and cce.component_inst_id = cpc.component_inst_id
   and p.component_id = cce.component_id
   and cce.component_id = cpc.component_id
   and cpc.inactive_dt       is null
   and p.product_inactive_dt is null
   and p.element_id = d.description_code
   and d.language_code = 2
   and substr(d.description_text,1,7) not in ('Descone','Ajuste ','Reversã','Contrat','Backout','Desc. d','Estorno','NRC Deb','Rev. de','Back Re')
   and d.description_code like '1%'
   -- 1) Franquia nível conta para todos os planos menos Lavoisier
   and (((d.description_text like '%Franquia Mensal%' or d.description_text like '%Plano Básico%') 
          and upper(d.description_text) not like '%CELULAR%') 
   -- 2) A Franquia nível conta Lavoisier para o 'GVT Ilimitado Local Casa'
        or d.description_text = 'Premium 50 - Franquia Mensal Favoritos celular' 
   -- 3) A Franquia nível conta Lavoisier para o 'GVT Ilimitado Total Casa'     
        or (p.component_id = 29917 and d.description_text = 'Franquia Celular')
   -- 4) A Franquia nível conta Lavoisier 'GVT Na Medida Nacional - Conta'     
        or (p.component_id = 30487 and d.description_text = 'GVT Na Medida Nacional')
   -- 5) Assinatura do Smart Maxx     
        or d.description_text = 'Assinatura Mensal Smart MAXX'
        ) 

union

select distinct cpc.component_id             component_id,  
                p.billing_active_dt          dt_ativacao, 
                cpc.component_inst_id        component_inst_id,
                cpc.package_id               package_id,
                d.description_text           arborcomponentdescription,
                'CSP'                        tipo_plano,
                'Conta'                      nivel_kenan
  from product                   p,
       cmf_component_element     cce,
       cmf_package_component     cpc,
       descriptions              d
 where p.parent_account_no = ?
   and p.billing_inactive_dt is null
   and p.tracking_id         = cce.association_id
   and cce.association_type  = 0
   and cce.component_inst_id = cpc.component_inst_id
   and p.component_id = cce.component_id
   and cce.component_id = cpc.component_id
   and cpc.inactive_dt       is null
   and p.product_inactive_dt is null
   and p.element_id = d.description_code
   and d.language_code = 2
   and (description_text like 'Ligue Local%' or 
        description_text like '25 Cidade Local%') -- CSP NIVEL CONTA DO LAVOISIER 'GVT Ilimitado ***** Casa'
) group by component_id,  
           dt_ativacao, 
           component_inst_id,
           package_id
)
