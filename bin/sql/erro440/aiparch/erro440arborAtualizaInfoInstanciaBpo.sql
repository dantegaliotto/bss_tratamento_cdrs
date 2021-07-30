update arp2$serviceinstancebpo
set bpstate = replace(bpstate, ?, 'SICreated')
where vtoid = ?
