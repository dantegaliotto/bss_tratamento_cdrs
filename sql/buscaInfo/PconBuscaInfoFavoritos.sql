/* QUERY ANTERIOR... NEM SEMPRE TEM RELAÇÃO COM A "instancia_servico_param" */
/*
select ip.designador instancia_cliente, 
       ss.nome status,
       iss.last_change ultima_alteracao
from conf_online_owner.instancia_produto ip
join conf_online_owner.instancia_servico iss on ip.id = iss.id_instancia_produto
join conf_online_owner.catalogo_servico cs on cs.id  = iss.id_servico 
                                          and cs.id in (500303, 500302, 500201)
join conf_online_owner.status_servico ss   on ss.id  = iss.id_status 
                                          and ss.nome = 'Ativado'
where ip.designador = ?
*/

select   ip.designador instancia_cliente, min(ss.nome) status, max(iss.last_change) ultima_alteracao
  from   conf_online_owner.instancia_produto ip, conf_online_owner.instancia_servico iss, conf_online_owner.instancia_servico_param issp,
         conf_online_owner.catalogo_servico cs, conf_online_owner.status_servico ss
 where   ip.id = iss.id_instancia_produto
         and issp.id_instancia_servico = iss.id
         and cs.id = iss.id_servico
         and ss.id = iss.id_status
         and cs.id in (500303, 500302)
         and ip.designador = ?
         and issp.parametro in
                  ('Telefone Local 1','Telefone Local 2','Telefone Local 3','Telefone LDN 1','Celular Local 1','Celular Local 2',
                   'Celular Local 3','Celular Local 4','Celular 1','Celular 2','Celular 3','Celular 4')
group by ip.designador

