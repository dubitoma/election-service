INSERT INTO candidates (number, first_name, last_name, agenda) VALUES
  (1, 'Donald', 'Trump', 'I will make america great again!'),
  (2, 'Rosie', 'Odonel', 'Im gonna make some pankakes!'),
  (3, 'Eric', 'Cartman', 'Cats are so cewl, bad mister kitty!'),
  (4, 'Barack', 'Obama', 'I will make people more healthy!'),
  (5, 'Mister', 'Worldwide', 'Why you think they call me Mr. Worldwide?'),
  (6, 'Eddie', 'Gordo', 'Brake dance is always brake dance, yo');

INSERT INTO voters (ssn, region) VALUES
  (131, 'NEW_ENGLAND'),
  (122, 'THE_SOUTH'),
  (123, 'THE_SOUTH'),
  (143, 'THE_WEST'),
  (454, 'MID_ATLANTIC'),
  (235, 'NEW_ENGLAND'),
  (656, 'THE_SOUTH');

 INSERT INTO votes (ballot_Id, created_on, issue, candidate_number, voter_ssn) VALUES
  (1, '2008-01-01 00:00:01', 'PRESIDENT', 1, 131),
  (12, '2008-01-01 00:00:01', 'PRESIDENT', 1, 122),
  (14, '2008-01-01 00:00:01', 'PRESIDENT', 2, 143),
  (15, '2008-01-01 00:00:01', 'PRESIDENT', 1, 123),
  (45, '2008-01-01 00:00:01', 'PRESIDENT', 2, 454);