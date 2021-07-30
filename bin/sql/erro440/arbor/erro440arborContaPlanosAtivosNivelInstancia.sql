select count(*) contagem from (

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
        exists (select 1 from bq_tp_arbor_csp where component_id = cpc.component_id and component_id <> 24006)
   and cpc.component_id      = m.arborcomponentid (+)
   
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

   ) 
   group by component_id, dt_ativacao, component_inst_id, package_id,tipo_plano, nivel_kenan
)

