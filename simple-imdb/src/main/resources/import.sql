insert into person (id,person_type,name,placeOfBirth,biography) values (1,'Artist','Lauren Cohan', 'Philadelphia, Pennsylvania, USA', 'Lauren Cohan is a British-American actress and model, best known for her role as Maggie Greene on The Walking Dead (2010) and recurring roles on The Vampire Diaries (2009), Supernatural (2005), and Chuck (2007).');
insert into person (id,person_type,name,placeOfBirth,biography) values (2,'Artist','Andrew Lincoln', ' London, England, UK', 'Andrew Lincoln is a British actor. Lincoln spent his early childhood in Hull, Yorkshire before his family relocated to Bath, Somerset when he was age 10. He was educated at Beechen Cliff School in Bath, and then the prestigious Royal Academy of Dramatic Art in London.');
insert into person (id,person_type,name,placeOfBirth,biography) values (3,'Artist','Alanna Masterson', 'Long Island, New York, USA', 'Alanna Masterson was born on June 27, 1988 in Long Island, New York, USA. She is an actress, known for The Walking Dead (2010), Mistresses (2013) and The Young and the Restless (1973).');
insert into person (id,person_type,name,placeOfBirth,biography) values (4,'Director','Frank Darabont', ' Montb�liard, Doubs, France', 'Three-time Oscar nominee Frank Darabont was born in a refugee camp in 1959 in Montbeliard, France, the son of Hungarian parents who had fled Budapest during the failed 1956 Hungarian revolution. Brought to America as an infant, he settled with his family in Los Angeles and attended Hollywood High School');
insert into person (id,person_type,name,placeOfBirth,biography) values (13,'Director','Frank Darabont Jr II', ' Montb�liard, Doubs, France', 'Three-time Oscar nominee Frank Darabont was born in a refugee camp in 1959 in Montbeliard, France, the son of Hungarian parents who had fled Budapest during the failed 1956 Hungarian revolution. Brought to America as an infant, he settled with his family in Los Angeles and attended Hollywood High School');

insert into studio (id,name,location,description) values(5,'AMC','New York City, NY','AMC is an American basic cable and satellite television channel that is owned by AMC Networks. The channel s programming, similar to that of FXM, primarily consists of theatrically released films, along with a limited amount of original programming');
insert into studio (id,name,location,description) values(14,'Jony Brothers','Austin, TY','Jony Brothers is an American basic cable and satellite television channel that is owned by AMC Networks. The channel s programming, similar to that of FXM, primarily consists of theatrically released films, along with a limited amount of original programming');

insert into tvseries (id,studio_id,director_id,name,description) values(6,5,4,'The Walking Dead','Sheriff Deputy Rick Grimes gets shot and falls into a coma. When awoken he finds himself in a Zombie Apocalypse. Not knowing what to do he sets out to find his family, after he is done that he gets connected to a group to become the leader. He takes charge and tries to help this group of people survive, find a place to live, and get them food. This show is all about survival, the risks, and the things you will have to do to survive.');
insert into tvseries (id,studio_id,director_id,name,description) values(15,14,13,'On the Mar','Sheriff Deputy Rick Grimes gets shot and falls into a coma. When awoken he finds himself in a Zombie Apocalypse. Not knowing what to do he sets out to find his family, after he is done that he gets connected to a group to become the leader. He takes charge and tries to help this group of people survive, find a place to live, and get them food. This show is all about survival, the risks, and the things you will have to do to survive.');
insert into tvseries (id,studio_id,director_id,name,description) values(16,5,4,'On the Moon','Sheriff Deputy Rick Grimes gets shot and falls into a coma. When awoken he finds himself in a Zombie Apocalypse. Not knowing what to do he sets out to find his family, after he is done that he gets connected to a group to become the leader. He takes charge and tries to help this group of people survive, find a place to live, and get them food. This show is all about survival, the risks, and the things you will have to do to survive.');
insert into tvseries (id,studio_id,director_id,name,description) values(17,14,13,'On the Earth','Sheriff Deputy Rick Grimes gets shot and falls into a coma. When awoken he finds himself in a Zombie Apocalypse. Not knowing what to do he sets out to find his family, after he is done that he gets connected to a group to become the leader. He takes charge and tries to help this group of people survive, find a place to live, and get them food. This show is all about survival, the risks, and the things you will have to do to survive.');
insert into tvseries_genres(tvseries_id, genres) values(6,'Drama');
insert into tvseries_genres(tvseries_id, genres) values(6,'Horror');
insert into tvseries_genres(tvseries_id, genres) values(6,'Thriller');
insert into tvseries_genres(tvseries_id, genres) values(15,'Drama');
insert into tvseries_genres(tvseries_id, genres) values(16,'Horror');
insert into tvseries_genres(tvseries_id, genres) values(17,'Thriller');

insert into season(id,tvseries_id,year,summary) values(7,6,2017,'Summary of 2017');
insert into season(id,tvseries_id,year,summary) values(8,6,2018,'Summary of 2018');
insert into season(id,tvseries_id,year,summary) values(18,15,2017,'Summary of 2017');
insert into season(id,tvseries_id,year,summary) values(19,16,2017,'Summary of 2017');
insert into season(id,tvseries_id,year,summary) values(20,17,2017,'Summary of 2017');

insert into episode(id,season_id,arrivalDate,name,description) values(9,7,'2017-01-18','Swear','Having been brutally overpowered by Negan and his Saviors, Rick and the group kneel helplessly as they suffer heavy losses that will haunt them forever.');
insert into episode(id,season_id,arrivalDate,name,description) values(10,7,'2017-04-18','Sing Me a Song','Carol and Morgan are brought to a community called the Kingdom, led by the eccentric King Ezekiel.');
insert into episode(id,season_id,arrivalDate,name,description) values(11,8,'2018-01-18','Hearts Still Beating','Rick and his group, along with the Kingdom and Hilltop, band together to bring the fight to Negan and the Saviors.');
insert into episode(id,season_id,arrivalDate,name,description) values(12,8,'2018-03-18','Rock in the Road','The plan involving Alexandrians, Kingdommers and Hilltoppers unfolds; As Rick continues to fight, he encounters a familiar face.');
insert into episode(id,season_id,arrivalDate,name,description) values(21,18,'2017-01-18','Swear','Having been brutally overpowered by Negan and his Saviors, Rick and the group kneel helplessly as they suffer heavy losses that will haunt them forever.');
insert into episode(id,season_id,arrivalDate,name,description) values(22,19,'2017-01-18','Swear','Having been brutally overpowered by Negan and his Saviors, Rick and the group kneel helplessly as they suffer heavy losses that will haunt them forever.');
insert into episode(id,season_id,arrivalDate,name,description) values(23,20,'2017-01-18','Swear','Having been brutally overpowered by Negan and his Saviors, Rick and the group kneel helplessly as they suffer heavy losses that will haunt them forever.');


insert into episodecharacter(id,person_id,episode_id,name,description) values(24,2,9,'Jame Smith','Jame Smith is a very strong person');
insert into episodecharacter(id,person_id,episode_id,name,description) values(25,2,9,'Mike Lancy','Mike Lance is a truthful person');
insert into episodecharacter(id,person_id,episode_id,name,description) values(26,1,10,'Mike North','Mike North was born in north pole');
insert into episodecharacter(id,person_id,episode_id,name,description) values(27,3,10,'Anna South','Anna South likes to go south');
insert into episodecharacter(id,person_id,episode_id,name,description) values(28,2,11,'Lucy Sky','Lucy does not like the sky');
insert into episodecharacter(id,person_id,episode_id,name,description) values(29,3,11,'Lucky Man','Having been brutally overpowered by Negan and his Saviors');
insert into episodecharacter(id,person_id,episode_id,name,description) values(30,3,12,'Ron Smith','Rick and his group, along with the Kingdom and Hilltop, band together to bring the fight to Negan');
insert into episodecharacter(id,person_id,episode_id,name,description) values(31,2,12,'Happy Mag','Having been brutally overpowered by Negan and his Saviors');

-- MySQL/Oracle
-- UPDATE hibernate_sequence SET next_val = 1000;