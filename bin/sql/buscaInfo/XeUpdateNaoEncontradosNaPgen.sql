update bss_440_cdrs_favoritos
   set tu_ok = decode(tu1,271,1200,412,402,643,617,tu1)
 where tu1 in (271, 412, 643)
   and tu_ok = 0
