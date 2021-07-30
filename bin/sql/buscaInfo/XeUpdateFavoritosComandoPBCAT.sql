update bss_440_cdrs_favoritos
set comando_pbcat = 'update cdr_data_work set type_id_usg = '|| tu_ok ||', orig_type_id_usg = '|| tu_ok ||', annotation = ''G0032485 - CORREÇÃO FAVORITOS TYPE_ID_USG DE '|| tu1 || ' PARA '|| tu_ok || ''' where type_id_usg = '|| tu1 ||' and msg_id = '|| msg_id ||' and msg_id2 = '|| msg_id2 ||' and msg_id_serv = '|| msg_id_serv ||' and miu_disp_status <> 0 and miu_disp_code in (0,1);'
where tu_ok <> 0
