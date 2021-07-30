 select arborcomponentid
   from mapeamentodecomponentes
  where siebelcomponentdescription = ?

union

 select arborcomponentid 
   from mapeamentodecomponentes
  where (siebelcomponentdescription LIKE 'SERVIÇO VOX 0800%+%' or 
         siebelcomponentdescription LIKE 'VOX 0800%+%')
    and substr(siebelcomponentdescription,1,instr(siebelcomponentdescription,'+')-1) = ?