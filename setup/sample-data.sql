INSERT INTO APP.PROJET (ID, DATEDEB, DESCRIPTION, NOM)
VALUES (1, CURRENT_DATE, '1e projet', 'Projet 1');
 
INSERT INTO APP.PROJET (ID, DATEDEB, DESCRIPTION, NOM)
VALUES (2, CURRENT_DATE, '2e projet', 'Projet 2');
 
INSERT INTO APP.PROJET (ID, DATEDEB, DESCRIPTION, NOM)
VALUES (3, CURRENT_DATE, '3e projet', 'Projet 3');
 
INSERT INTO APP.PROJET (ID, DATEDEB, DESCRIPTION, NOM)
VALUES (4, CURRENT_DATE, '4e projet', 'Projet 4');
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (1, '1', CURRENT_DATE, '1ere tache', 0, 'Tache1', 10, 130, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (2, '0', CURRENT_DATE, '2e tache', 1, 'Tache2', 20, 170, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (3, '1', CURRENT_DATE, '3e tache', 2, 'Tache3', 30, 110, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (4, '0', CURRENT_DATE, '4e tache', 0, 'Tache4', 40, 120, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (5, '1', CURRENT_DATE, '5e tache', 1, 'Tache5', 50, 130, CURRENT_DATE, NULL, 2);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (6, '0', CURRENT_DATE, '6e tache', 2, 'Tache6', 60, 130, CURRENT_DATE, NULL, 2);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (7, '1', CURRENT_DATE, '7e tache', 0, 'Tache7', 70, 10, CURRENT_DATE, NULL, 3);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (8, '0', CURRENT_DATE, '8e tache', 0, 'Tache8', 80, 100, CURRENT_DATE, NULL, 4);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (9, '1', CURRENT_DATE, '9e tache', 2, 'Tache9', 90, 130, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (10, '0', CURRENT_DATE, '10e tache', 0, 'Tache10', 100, 143, CURRENT_DATE, NULL, 1);
 
INSERT INTO APP.TACHE (ID, ARCHIVE, DATEDEB, DESCRIPTION, IMPORTANCE, NOM, POURCENTAGE, REVISION, TEMPSPASSESURTACHE, TIMERLAUNCHED, PROJET)
VALUES (11, '0', CURRENT_DATE, '11e tache', 0, 'Tache11', 0, 154, CURRENT_DATE, NULL, 1);