select b3.external_id, 
    greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')) ) dt_desativar,
    b3.componentid component_id, 
    b1.serverid server_id, 
    b3.subscrno subscrno, 
    b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where     b1.processo = 'Erro440' and b3.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.tipoplano = 'R'
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   and b1.componentid > 0 -- and b1.cspid_kenan > 0 
   and b3.componentid > 0
   and instr(b1.componentid || ' ' || b1.cspid_kenan, b3.componentid) = 0

/* incluido acima a desativacao pelo CSP
select b3.external_id, 
    b1.primeirocdr dt_desativar, b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno, b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where     b1.processo = 'Erro440' and b3.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   and b1.componentid <> 0 and b3.componentid <> 0
   and parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi = 0
   and b1.componentid <> b3.componentid
   
      and parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi > 0
   and instr(parcomponentidlocal || ' ' || parcomponentidvc || ' ' || parcomponentidldn || ' ' || parcomponentidldi, b3.componentid) = 0
*/
   
   /*  não desativa mais pela data do plano / data do cdr pois a data do plano do siebel 
    *  pode estar errada fazendo com que os clientes regulares fiquem sem plano
    
union
select b3.external_id, 
    case when ( b3.dtplano >= b1.dtplano or b3.dtplano >= b1.primeirocdr) then
        b3.dtplano
    else
        case when (b1.primeirocdr <= b1.dtplano) then
            b1.primeirocdr
        else
            b1.dtplano
        end 
    end dt_desativar, b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno, b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where     b1.processo = 'Erro440' and b3.processo = 'Erro440'
--           and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   and parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi = 0
   and b1.componentid = b3.componentid
   and trunc(b1.primeirocdr) < trunc(b3.dtplano)
*/
   
   
   /* calculo anterior da data desativar
   
   case when ( b3.dtplano >= b1.dtplano or b3.dtplano >= b1.primeirocdr) then
        b3.dtplano
    else
        case when (b1.primeirocdr <= b1.dtplano) then
            b1.primeirocdr
        else
            b1.dtplano
        end 
    end dt_desativar
   
   */