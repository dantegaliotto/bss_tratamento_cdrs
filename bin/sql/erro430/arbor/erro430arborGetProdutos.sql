
SELECT cpc.component_inst_id,
  cpc.component_id,
  def.display_value,
  cpc.active_dt,
  cpc.inactive_dt,
  MAX(pcm.billed_thru_dt) ultimo_fat,
  cpc.package_id package_id
FROM cmf_package_component cpc,
  component_definition_values def,
  cmf_component_element cce,
  product p,
  product_charge_map pcm
WHERE cpc.parent_subscr_no                = ?
AND NVL(cpc.inactive_dt, TRUNC(sysdate)) >= ?
AND cpc.active_dt                        <= ?
AND cpc.component_id                      = def.component_id
AND def.language_code                     = 2
AND cpc.component_inst_id                 = cce.component_inst_id
AND cpc.component_inst_id_serv            = cce.component_inst_id_serv
AND cce.association_id                    = p.tracking_id
AND cce.association_id_serv               = p.tracking_id_serv
AND p.tracking_id                         = pcm.tracking_id
AND cpc.component_id                     IN
  (SELECT component_id
  FROM package_component_members
  WHERE member_id IN
    (SELECT element_id
    FROM rate_usage
    WHERE element_id IN
      (SELECT element_id
      FROM product ep
      WHERE ep.parent_subscr_no                        = ?
      AND NVL(ep.product_inactive_dt, TRUNC(sysdate)) >= ?
      AND ep.product_active_dt                        <= ?
      )
    AND type_id_usg   = ?
    AND inactive_dt  IS NULL
    AND (jurisdiction = ?
    OR jurisdiction   = 0)
    )
  )
GROUP BY cpc.component_inst_id,
  def.display_value,
  cpc.active_dt,
  cpc.inactive_dt,
  cpc.component_id,
  cpc.package_id


/* processo contenda verificacao de duplicados */
/*
select 0 component_inst_id,
  cpc.component_id,
  def.display_value,
  cpc.active_dt,
  cpc.inactive_dt,
  trunc(sysdate) ultimo_fat,
  cpc.package_id package_id,
  count(*)
from cmf_package_component cpc, component_definition_values def
 where cpc.parent_subscr_no = ?
 and cpc.inactive_dt is null
AND cpc.component_id                      = def.component_id
AND def.language_code                     = 2
AND cpc.component_id                     IN (
  SELECT component_id
  FROM package_component_members
  WHERE member_id IN (
    SELECT element_id
    FROM rate_usage
    WHERE element_id IN (
          SELECT element_id
            FROM product ep
           WHERE ep.parent_subscr_no = ?
             AND product_inactive_dt is null
                         )
      AND inactive_dt  IS NULL
    )
) group by 0,
  cpc.component_id,
  def.display_value,
  cpc.active_dt,
  cpc.inactive_dt,
  trunc(sysdate),
  cpc.package_id
  having count(*) > 1  */

/*  QUERY APENAS PARA O PROCESSO DO CONTENDA que BUSCA os duplicados */ 
/*
select cpc.component_inst_id,
  cpc.component_id,
  def.display_value,
  cpc.active_dt,
  cpc.inactive_dt,
  trunc(sysdate) ultimo_fat,
  cpc.package_id package_id
from cmf_package_component cpc, component_definition_values def
 where cpc.parent_subscr_no = ?
 and cpc.inactive_dt is null
AND cpc.component_id                      = def.component_id
AND def.language_code                     = 2
AND cpc.component_id                     IN (
  SELECT component_id
  FROM package_component_members
  WHERE member_id IN (
    SELECT element_id
    FROM rate_usage
    WHERE element_id IN (
          SELECT element_id
            FROM product ep
           WHERE ep.parent_subscr_no = ?
             AND product_inactive_dt is null
                         )
      AND inactive_dt  IS NULL
    )
)      

*/

