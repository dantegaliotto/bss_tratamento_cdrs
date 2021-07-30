-- GUIADORES
select distinct p.component_id        component_id, 
                p.billing_active_dt   dt_ativacao, 
                cpc.component_inst_id component_inst_id, 
                cpc.package_id        package_id, 
                cdv.display_value     arborcomponentdescription, 
                'Guiador'             tipo_plano, 
                'Instância'           nivel_kenan
from product p
join cmf_component_element cce       on p.tracking_id = cce.association_id and cce.association_type = 1 and p.billing_inactive_dt is null
join cmf_package_component cpc       on cce.component_inst_id = cpc.component_inst_id and cpc.inactive_dt is null
join component_definition_values cdv on cdv.component_id = cpc.component_id and cdv.language_code = 2 and cpc.inactive_dt is null
join rate_usage r                    on r.inactive_dt is null and r.element_id = p.element_id
where p.parent_subscr_no = ?
              --) --,'3760772','22696070','5928941','19695692')'21338119'
  and p.product_inactive_dt is null
--order by p.parent_subscr_no

union
-- NÃO GUIADORES
select distinct p.component_id        component_id, 
                p.billing_active_dt   dt_ativacao, 
                cpc.component_inst_id component_inst_id, 
                cpc.package_id        package_id, 
                cdv.display_value     arborcomponentdescription, 
                'Não Guiador'         tipo_plano, 
                'Instância'           nivel_kenan
from product p
join cmf_component_element cce       on p.tracking_id = cce.association_id and cce.association_type = 1 and p.billing_inactive_dt is null
join cmf_package_component cpc       on cce.component_inst_id = cpc.component_inst_id and cpc.inactive_dt is null
join component_definition_values cdv on cdv.component_id = cpc.component_id and cdv.language_code = 2 and cpc.inactive_dt is null
left join rate_usage r               on r.inactive_dt is null and r.element_id = p.element_id
where p.parent_subscr_no = ?
  and p.component_id in (30362)
  and p.product_inactive_dt is null
  and r.element_id is null

/* QUERY ANTIGA -- FOI SUBSTITUIDA PELA QUERY QUE BUSCA SOMENTE GUIADORES
select distinct 
       component_id, dt_ativacao,component_inst_id,package_id,
       min(arborcomponentdescription) arborcomponentdescription,
       tipo_plano, nivel_kenan 
  from (
select distinct cpc.component_id             component_id,  
                p.billing_active_dt          dt_ativacao, 
                cpc.component_inst_id        component_inst_id,
                cpc.package_id               package_id,
                m.arborcomponentdescription  arborcomponentdescription,
                'Plano não CSP'              tipo_plano,
                'Instância'                  nivel_kenan
  from product                   p,
       product_elements          pe,
       cmf_component_element     cce,
       cmf_package_component     cpc,
       mapeamentodecomponentes   m
 where p.parent_subscr_no    = ?
   and p.element_id          = pe.element_id
   and p.tracking_id         = cce.association_id
   and cce.association_type  = 1
   and cce.component_inst_id = cpc.component_inst_id
   and p.component_id = cce.component_id
   and cce.component_id = cpc.component_id
   and cpc.inactive_dt       is null
   and p.product_inactive_dt is null
   and p.element_id not in (10303,10304,10305,10331,10333,10297,10298,10299) -- RETIRA OS CASOS LAVOISIER
   and (exists (select 1 from product_elements pe, usage_type_groups ut
                 where pe.element_id = p.element_id
                   and pe.type_group_usg = ut.type_group_usg)
        and
        not exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id)
        and cpc.component_id not in (23233, 23231, 23329,23328,23226,23225,23422,24369,23424)) 
                     -- retira a secretaria eletronica e 
                     -- pacote economico id. chamadas do processamento
   and cpc.component_id      = m.arborcomponentid (+)
   
union
   
select distinct cpc.component_id             component_id,  
                p.billing_active_dt          dt_ativacao, 
                cpc.component_inst_id        component_inst_id,
                cpc.package_id               package_id,
                m.arborcomponentdescription  arborcomponentdescription,
                'Plano CSP'                  tipo_plano,
                'Instância'                  nivel_kenan
  from product                   p,
       product_elements          pe,
       cmf_component_element     cce,
       cmf_package_component     cpc,
       mapeamentodecomponentes   m
 where p.parent_subscr_no    = ?
   and p.element_id          = pe.element_id
   and p.tracking_id         = cce.association_id
   and cce.association_type  = 1
   and cce.component_inst_id = cpc.component_inst_id
   and p.component_id = cce.component_id
   and cce.component_id = cpc.component_id
   and cpc.inactive_dt       is null
   and p.product_inactive_dt is null
   and 
       exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id
                                               and component_id <> 24006) -- 2014-11-12: RETIRADA CARGA CSP25 PPR
   and cpc.component_id      = m.arborcomponentid (+)
--   and cc
   
union

select distinct cpc.component_id             component_id,  
                p.billing_active_dt          dt_ativacao, 
                cpc.component_inst_id        component_inst_id,
                cpc.package_id               package_id,
                d.description_text           arborcomponentdescription,
                'Plano Lavoisier'            tipo_plano,
                'Instância'                   nivel_kenan
  from product                   p,
       product_elements          pe,
       cmf_component_element     cce,
       cmf_package_component     cpc,
       descriptions              d
 where p.parent_subscr_no = ?
   and p.billing_inactive_dt is null
   and p.element_id in (10303,10304,10305,10331,10333,10297,10298,10299)  -- CASOS LAVOISIER
   and p.element_id          = pe.element_id
   and p.tracking_id         = cce.association_id
   and cce.association_type  = 1
   and cce.component_inst_id = cpc.component_inst_id
   and p.component_id = cce.component_id
   and cce.component_id = cpc.component_id
   and cpc.inactive_dt       is null
   and p.product_inactive_dt is null
   and p.element_id = d.description_code
   and d.language_code = 2

union

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
   -- 6) Assinatura Mensal sem Minutos - GVT Na Medida Nacional     
        or d.description_text = 'Assinatura Mensal sem Minutos - GVT Na Medida Nacional'
--   -- 7) Franquia Unique     
--        or d.description_text = 'Franquia Unique 300'  -- não vai tratar os produtos nível conta do Unique pois estão muito divergentes
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
--        description_text like 'Plano CSP 25%') -- não vai tratar os produtos nível conta do Unique pois estão muito divergentes
   ) -- agrupa as informações dos 5 selects para trazer apenas um nome de produto para cada component_id
   group by component_id, dt_ativacao, component_inst_id, package_id,tipo_plano, nivel_kenan
*/      