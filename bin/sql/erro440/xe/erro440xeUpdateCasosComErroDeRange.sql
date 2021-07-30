update bss_tratamento_cdrs_load
set log = 'Instancia com erro de range no Siebel (Barigui)', resultado = -2
WHERE designador = 'CTA-30IV588Y-031' or RPON = '3-X86AOI' or designador = 'CTA-30X802QW-031'
and created = trunc(sysdate)
