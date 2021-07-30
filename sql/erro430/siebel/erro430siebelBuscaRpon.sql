SELECT
  /*+ rule */
  row_id
FROM gvtprd.s_quote_soln a
WHERE a.asset_num = ?
AND a.copied_flg  = 'N'
ORDER BY DECODE(status_cd, 'Ativo', 0, 'Ativo Parcial', 1, 'Pendente', 2, 'Retida', 3, 'Desconectar', 4, 'C', 5, 'Cancelado', 6, 'Cacelado', 7, 'Cancelar', 8, 'Canclado', 9, 'I', 10, 'Inativo', 11, 'Deleted', 12, 13) ASC,
  DECODE(action_cd, 'Ativo', 0, 'Adicionar', 1, 'Adiconar', 2, 'Pendente', 3, 'Modificar', 4, 'Desconectar', 5, 'Deconectar', 6, 'Cancelado', 7, 'Cancelar', 8, 'Inativo', 9, 10) ASC 