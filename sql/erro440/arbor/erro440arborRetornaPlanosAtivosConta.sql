select distinct 
       component_id, dt_ativacao,component_inst_id,package_id,
       min(arborcomponentdescription) arborcomponentdescription,
       tipo_plano, nivel_kenan 
  from (

select distinct cpc.component_id             component_id,  
                cpc.active_dt                dt_ativacao, 
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
   and (d.description_text like '%Franquia Mensal%' or 
        d.description_text like '%Plano Básico%')
   and upper(d.description_text) not like '%CELULAR%' -- não traz info de franquia celular

union

select distinct cpc.component_id             component_id,  
                cpc.active_dt                dt_ativacao, 
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
   and description_text like 'Ligue Local%'
   ) -- agrupa as informações dos 2 selects para trazer apenas um nome de produto para cada component_id
   group by component_id, dt_ativacao, component_inst_id, package_id,tipo_plano, nivel_kenan   