SELECT distinct jurisdiction
FROM cdr_data_work
WHERE external_id = ?
AND msg_id        = ?