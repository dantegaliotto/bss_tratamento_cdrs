select external_id, trunc(sum(valor),2) valor
from cdrs_work
where tipo_erro = 152
and cenario = 'VOX800RI Ativo no Siebel com estado divergente da Central' 
group by external_id
