SELECT subscr_no, server_id
FROM external_id_equip_map eiem
WHERE eiem.external_id = ?
AND inactive_date     IS NULL
AND external_id_type   = 6