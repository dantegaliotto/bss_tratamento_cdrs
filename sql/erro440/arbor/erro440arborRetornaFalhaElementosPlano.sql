select * from package_component_members pcm
 where component_id = ?
   and inactive_dt is null
   and member_id like '1%'
   and member_type = 1
   and member_id not in (select element_id
                           from product
                          where parent_subscr_no = ?
                            and billing_inactive_dt is null
                            and component_id = ?
                            and tracking_id in (select association_id 
                                                  from cmf_component_element 
                                                 where component_inst_id = ?
                                                   and component_id = ?)
                          )
