select b3.external_id, 
    case when ( b3.dtplano >= b1.data_plano_siebel or b3.dtplano >= b1.primeirocdr) then
        b3.dtplano
    else
        case when (b1.primeirocdr <= b1.data_plano_siebel) then
            b1.primeirocdr
        else
            b1.data_plano_siebel
        end 
    end dt_desativar, b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno
from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
where b1.created = trunc(sysdate) and b3.created = trunc(sysdate)
and b1.external_id = b3.instancia
and parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi > 0
and instr(parcomponentidlocal || ' ' || parcomponentidvc || ' ' || parcomponentidldn || ' ' || parcomponentidldi, b3.componentid) = 0
