select count(*) num_designadores from ( 
select distinct designador from BSS_SB_RANGE_RAMAIS
where ? between range_inicial and range_final
AND  RANGE_INICIAL IS NOT NULL 
AND  RANGE_FINAL IS NOT NULL 
AND  SUBSTR(TRIM(RANGE_INICIAL),1,5) = SUBSTR(TRIM(RANGE_FINAL),1,5)
and length(range_inicial) = length(range_final)
AND COPIED_FLG = 'N' 
and status_sqi = 'Ativo'
and status_sqs = 'Ativo'
)