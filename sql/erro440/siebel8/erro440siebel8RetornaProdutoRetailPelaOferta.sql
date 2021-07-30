select 'busca2' row_id, 
       replace(replace(spi.name,' Oferta',''),' Retencao','') name, 
       spi.last_upd eff_end_dt
  from SIEBEL.S_ASSET SA
  join SIEBEL.S_PROD_INT SPI on sa.promotion_id = SPI.ROW_ID
 where SA.SERIAL_NUM = ?
   and SA.STATUS_CD = 'Ativo'
