select quando, old_no_bill, new_no_bill, old_remark || ' -> ' || new_remark remark
from GVT_LOG_CMF
where QUANDO >= '01/01/2012'
AND OLD_NO_BILL <> NEW_NO_BILL
and upper(old_remark || new_remark) like '%INADIMP%'
AND account_no  = ?
