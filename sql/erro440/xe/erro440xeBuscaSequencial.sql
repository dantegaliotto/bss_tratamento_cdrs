select max(seq) sequencial from (
select nvl(max(seq),1000) + 1 seq
from bss_tratamento_cdrs_load
union all
select nvl(max(seq),1000) + 1 seq
from bss_tratamento_cdrs)
