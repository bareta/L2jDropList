SET TRACE_LEVEL_FILE 2;
SELECT * FROM npc WHERE name like '%vorpal%' ORDER BY name;
SELECT id,nome,icon FROM items WHERE nome like '%vorpal%' ORDER BY nome;
SET TRACE_LEVEL_FILE 2;
select * from items where id=15755;
SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=15755)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
SET TRACE_LEVEL_FILE 2;
select * from npc where id=22854;
SET TRACE_LEVEL_FILE 2;
SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=22854)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
SELECT * FROM npc WHERE name like '%karik%' ORDER BY name;
SELECT id,nome,icon FROM items WHERE nome like '%karik%' ORDER BY nome;
SET TRACE_LEVEL_FILE 2;
SET TRACE_LEVEL_FILE 2;
select * from npc where id=18008;
SET TRACE_LEVEL_FILE 2;
SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=18008)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
SET TRACE_LEVEL_FILE 2;
select * from npc where id=20629;
SET TRACE_LEVEL_FILE 2;
SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=20629)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
select locx,locy,npc_templateid from spawnlist where npc_templateid=20629;
SET TRACE_LEVEL_FILE 2;
select * from items where id=8345;
SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=8345)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
SET TRACE_LEVEL_FILE 2;
select * from npc where id=18551;
SET TRACE_LEVEL_FILE 2;
SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=18551)) order by droplist.category desc, droplist.chance desc;
SET TRACE_LEVEL_FILE 2;
select * from items where id=1871;
SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=1871)) order by droplist.category desc, droplist.chance desc;
-----------------------------------------
-- SQL Statement Statistics
-- time: total time in milliseconds (accumulated)
-- count: how many times the statement ran
-- result: total update count or row count
-----------------------------------------
-- self accu    time   count  result sql
--  15%  15%    5752       1       8 SELECT id,nome,icon FROM items WHERE nome like '%karik%' ORDER BY nome;
--  13%  28%    4975       1      44 SELECT id,nome,icon FROM items WHERE nome like '%vorpal%' ORDER BY nome;
--  12%  40%    4854       1      27 SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=18551)) order by droplist.category desc, droplist.chance desc;
--  10%  51%    4076       1      20 SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=20629)) order by droplist.category desc, droplist.chance desc;
--  10%  61%    4043       1      20 SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=22854)) order by droplist.category desc, droplist.chance desc;
--  10%  72%    3861       1       0 SELECT * FROM npc WHERE name like '%vorpal%' ORDER BY name;
--  10%  82%    3844       1       3 SELECT * FROM npc WHERE name like '%karik%' ORDER BY name;
--   5%  87%    2215       1     356 SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=1871)) order by droplist.category desc, droplist.chance desc;
--   4%  92%    1799       1       1 SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=8345)) order by droplist.category desc, droplist.chance desc;
--   3%  96%    1498       1       4 SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid WHERE (((droplist.itemid)=15755)) order by droplist.category desc, droplist.chance desc;
--   2%  98%     913       1       1 select * from items where id=15755;
--   0%  99%     218       1       1 select * from items where id=8345;
--   0%  99%     173       1       1 select * from items where id=1871;
--   0%  99%       4       1       1 select * from npc where id=20629;
--   0%  99%       3       1       1 select * from npc where id=18008;
--   0%  99%       1       1       1 select * from npc where id=18551;
--   0%  99%       1       1       1 select * from npc where id=22854;
--   0% 100%       1       1       2 select locx,locy,npc_templateid from spawnlist where npc_templateid=20629;
--   0% 100%       0      18       0 SET TRACE_LEVEL_FILE 2;
--   0% 100%       0       1       0 SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance FROM droplist LEFT JOIN items ON droplist.itemid = items.id WHERE (((droplist.mobId)=18008)) order by droplist.category desc, droplist.chance desc;
