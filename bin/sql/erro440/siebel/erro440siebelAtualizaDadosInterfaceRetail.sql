update sel_corporate
   set status = 0
 where row_id = ?
   and event_code = 4
