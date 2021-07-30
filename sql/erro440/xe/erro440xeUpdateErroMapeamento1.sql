update bss_tratamento_cdrs 
set parcomponentidldi = 23843 
where created = trunc(sysdate) 
and processo = 'Erro440' 
and ldi = 'Plano LDI 1 (60+6)'