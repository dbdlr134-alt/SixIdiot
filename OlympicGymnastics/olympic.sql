-- 국적정보 테이블 (tbl_olympic_nation) [cite: 10]
CREATE TABLE tbl_olympic_nation (
    nation_code CHAR(1) NOT NULL PRIMARY KEY, -- 국적코드 (PK) [cite: 11]
    nation_name VARCHAR(20)                 -- 국적명 [cite: 11]
);

-- 기술난이도 테이블 (tbl_olympic_skill) [cite: 15]
CREATE TABLE tbl_olympic_skill (
    skill_level CHAR(1) NOT NULL PRIMARY KEY, -- 기술난이도 (PK) [cite: 16]
    skill_name VARCHAR(20)                  -- 기술상세명 [cite: 16]
);

-- 체조경기참가자정보 테이블 (tbl_olympic_player) [cite: 6]
CREATE TABLE tbl_olympic_player (
    player_no CHAR(4) NOT NULL PRIMARY KEY,     -- 선수번호 (PK) [cite: 7]
    name VARCHAR(20) NOT NULL,                  -- 이름 [cite: 7]
    birth CHAR(8) NOT NULL,                     -- 생년월일 [cite: 7]
    nation_code CHAR(1) NOT NULL,               -- 국적코드 [cite: 7]
    skill_level CHAR(1) NOT NULL,               -- 기술난이도 [cite: 7]
    FOREIGN KEY (nation_code) REFERENCES tbl_olympic_nation(nation_code), -- FK [cite: 10]
    FOREIGN KEY (skill_level) REFERENCES tbl_olympic_skill(skill_level) -- FK [cite: 15]
);

-- 심사위원 채점결과 정보 테이블 (tbl_olympic_point) [cite: 19]
CREATE TABLE tbl_olympic_point (
    player_no CHAR(4) NOT NULL PRIMARY KEY, -- 선수번호 (PK) 
    point_1 INT(2),                         -- 점수1 [cite: 20, 22]
    point_2 INT(2),                         -- 점수2 [cite: 20, 22]
    point_3 INT(2),                         -- 점수3 [cite: 20, 22]
    point_4 INT(2),                         -- 점수4 [cite: 20, 22]
    total INT(2),                           -- 총점 
    ave DECIMAL(4, 2),                      -- 평균  (소수점 둘째 자리까지 [cite: 83])
    FOREIGN KEY (player_no) REFERENCES tbl_olympic_player(player_no) -- FK [cite: 6]
);

-- 국적정보 샘플 데이터 [cite: 13]
INSERT INTO tbl_olympic_nation (nation_code, nation_name) VALUES
('K', '대한민국'),
('A', '미국'),
('C', '중국'),
('J', '일본'),
('P', '아프간');

-- 기술난이도 샘플 데이터 [cite: 18]
INSERT INTO tbl_olympic_skill (skill_level, skill_name) VALUES
('1', '자빠지기'),
('2', '옆구르기'),
('3', '뒤구르기'),
('4', '1회전넘어지기'),
('5', '2회전자빠지기');

-- 참가자정보 샘플 데이터 [cite: 9]
INSERT INTO tbl_olympic_player (player_no, name, birth, nation_code, skill_level) VALUES
('1001', '신재환', '19970101', 'K', '5'),
('1002', '왕쯔이', '19980201', 'C', '4'),
('2003', '요시키', '19990301', 'J', '2'),
('2004', '산드라', '20000401', 'A', '3'),
('1005', '우루메', '20010501', 'P', '5');

-- 채점결과 샘플 데이터 
INSERT INTO tbl_olympic_point (player_no, point_1, point_2, point_3, point_4, total, ave) VALUES
('1001', 9, 8, 9, 7, 24, 8.40),
('1002', 8, 10, 9, 6, 23, 7.96),
('2003', 10, 9, 8, 6, 23, 7.81),
('2004', 9, 10, 7, 7, 23, 7.88);

-- 데이터베이스 변경사항 저장
COMMIT;
