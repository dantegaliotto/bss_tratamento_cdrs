update bss_tratamento_cdrs
set resultado = -2, 
log = 'Erro: Instancia pertencente ao ciclo ' || ?
where created = trunc(sysdate)
and ciclo = ?
