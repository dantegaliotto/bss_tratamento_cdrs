select cenario 
from view_cdr_em_erro
and tipo_erro in (440, 430)
where external_id = ?