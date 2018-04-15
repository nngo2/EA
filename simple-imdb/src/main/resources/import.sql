insert into person (id,person_type,name,placeOfBirth,biography) values (1,'Artist','Lauren Cohan', 'Philadelphia, Pennsylvania, USA', 'Lauren Cohan is a British-American actress and model, best known for her role as Maggie Greene on The Walking Dead (2010) and recurring roles on The Vampire Diaries (2009), Supernatural (2005), and Chuck (2007).');
insert into person (id,person_type,name,placeOfBirth,biography) values (2,'Artist','Andrew Lincoln', ' London, England, UK', 'Andrew Lincoln is a British actor. Lincoln spent his early childhood in Hull, Yorkshire before his family relocated to Bath, Somerset when he was age 10. He was educated at Beechen Cliff School in Bath, and then the prestigious Royal Academy of Dramatic Art in London.');
insert into person (id,person_type,name,placeOfBirth,biography) values (3,'Artist','Alanna Masterson', 'Long Island, New York, USA', 'Alanna Masterson was born on June 27, 1988 in Long Island, New York, USA. She is an actress, known for The Walking Dead (2010), Mistresses (2013) and The Young and the Restless (1973).');
insert into person (id,person_type,name,placeOfBirth,biography) values (4,'Director','Frank Darabont', ' Montbéliard, Doubs, France', 'Three-time Oscar nominee Frank Darabont was born in a refugee camp in 1959 in Montbeliard, France, the son of Hungarian parents who had fled Budapest during the failed 1956 Hungarian revolution. Brought to America as an infant, he settled with his family in Los Angeles and attended Hollywood High School');

insert into studio (id,name,location,description) values(5,'AMC','New York City, NY','AMC is an American basic cable and satellite television channel that is owned by AMC Networks. The channel s programming, similar to that of FXM, primarily consists of theatrically released films, along with a limited amount of original programming');

insert into tvseries (id,studio_id,director_id,description) values(6,5,4,'Sheriff Deputy Rick Grimes gets shot and falls into a coma. When awoken he finds himself in a Zombie Apocalypse. Not knowing what to do he sets out to find his family, after he is done that he gets connected to a group to become the leader. He takes charge and tries to help this group of people survive, find a place to live, and get them food. This show is all about survival, the risks, and the things you will have to do to survive.');

insert into season(id,tvseries_id,year,summary) values(7,6,2017,'Summary of 2017');
insert into season(id,tvseries_id,year,summary) values(8,6,2018,'Summary of 2018');

insert into episode(id,season_id,arrivalDate,description) values(9,7,'2017-01-18', 'Having been brutally overpowered by Negan and his Saviors, Rick and the group kneel helplessly as they suffer heavy losses that will haunt them forever.');
insert into episode(id,season_id,arrivalDate,description) values(10,7,'2017-04-18', 'Carol and Morgan are brought to a community called the Kingdom, led by the eccentric King Ezekiel.');
insert into episode(id,season_id,arrivalDate,description) values(11,8,'2018-01-18', 'Rick and his group, along with the Kingdom and Hilltop, band together to bring the fight to Negan and the Saviors.');
insert into episode(id,season_id,arrivalDate,description) values(12,8,'2018-03-18', 'The plan involving Alexandrians, Kingdommers and Hilltoppers unfolds; As Rick continues to fight, he encounters a familiar face.');

insert into person_artist_series(person_id,tvseries_id) values(1,6);
insert into person_artist_series(person_id,tvseries_id) values(2,6);
insert into person_artist_series(person_id,tvseries_id) values(3,6);

insert into person_artist_episode(person_id,espisode_id) values(1,9);
insert into person_artist_episode(person_id,espisode_id) values(2,9);
insert into person_artist_episode(person_id,espisode_id) values(1,10);
insert into person_artist_episode(person_id,espisode_id) values(3,10);
insert into person_artist_episode(person_id,espisode_id) values(2,11);
insert into person_artist_episode(person_id,espisode_id) values(3,11);
insert into person_artist_episode(person_id,espisode_id) values(3,12);
insert into person_artist_episode(person_id,espisode_id) values(2,12);


-- MySQL/Oracle
-- UPDATE hibernate_sequence SET next_val = 1000;