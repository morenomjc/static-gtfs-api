CREATE SCHEMA IF NOT EXISTS `STATICGTFS`;

USE `STATICGTFS`;

DROP TABLE IF EXISTS `ENUM_VALUES`;
CREATE TABLE ENUM_VALUES(
   ID                   INT(12) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   FILE                 VARCHAR(50) NOT NULL,
   FIELD                VARCHAR(50) NOT NULL,
   CODE                 VARCHAR(50) NOT NULL,
   NAME                 VARCHAR(100) NOT NULL,
   DESCRIPTION          VARCHAR(200)
);

INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','location_type','0','Stop','A location where passengers board or disembark from a transit vehicle.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','location_type','1','Station','A physical structure or area that contains one or more stop.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','location_type','2','Station Entrance/Exit','A location where passengers can enter or exit a station from the street.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','wheelchair_boarding','0','No Information','No accessibility information for the stop.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','wheelchair_boarding','1','Possible But Not Guaranteed','Some vehicles at this stop can be boarded by a rider in a wheelchair.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stops','wheelchair_boarding','2','Not Possible','Wheelchair boarding is not possible at this stop.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','direction_id','0','Outbound','Travel in one direction (e.g. outbound travel).');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','direction_id','1','Inbound','Travel in the opposite direction (e.g. inbound travel).');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','wheelchair_accessible','0','No Information','No accessibility information for this trip.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','wheelchair_accessible','1','Possible For Only One','Vehicle being used on this particular trip can accommodate at least one rider in a wheelchair.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','wheelchair_accessible','2','Not Possible','No riders in wheelchairs can be accommodated on this trip.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','bikes_allowed','0','No Information','No bike information for the trip.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','bikes_allowed','1','Possible For Only One','Vehicle being used on this particular trip can accommodate at least one bicycle.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('trips','bikes_allowed','2','Not Possible','No bicycles are allowed on this trip.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','pickup_type','0','Regular','Regularly scheduled pickup.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','pickup_type','1','None','No pickup available.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','pickup_type','2','Contact Agency','Must phone agency to arrange pickup.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','pickup_type','3','Contact Driver','Must coordinate with driver to arrange pickup.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','drop_off_type','0','Regular','Regularly scheduled drop off.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','drop_off_type','1','None','No drop off available.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','drop_off_type','2','Contact Agency','Must phone agency to arrange drop off.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','drop_off_type','3','Contact Driver','Must coordinate with driver to arrange drop off.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','timepoint','0','Approximate','Times are considered approximate.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('stop_times','timepoint','1','Exact','Times are considered exact.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('calendar','day','0','Not Available','Service is not available for this day');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('calendar','day','1','Available','Service is available for this day');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('calendar_dates','exception_type','1','Added','Service has been added for the specified date.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('calendar_dates','exception_type','2','Removed','Service has been removed for the specified date.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','payment_method','0','During','Fare is paid on board.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','payment_method','1','Before Boarding','Fare must be paid before boarding.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','transfers','0','None','No transfers permitted on this fare.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','transfers','1','Once','Passenger may transfer once.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','transfers','2','Twice','Passenger may transfer twice.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('fare_attributes','transfers','*','Unlimited','Unlimited transfers are permitted.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('frequencies','exact_times','0','Not Exactly Scheduled','Frequency-based trips are not exactly scheduled.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('frequencies','exact_times','1','Exactly Scheduled','Frequency-based trips are exactly scheduled.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('transfers','transfer_type','0','Recommended','This is a recommended transfer point between routes.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('transfers','transfer_type','1','Timed','This is a timed transfer point between two routes.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('transfers','transfer_type','2','Minimum Time Required','This transfer requires a minimum amount of time between arrival and departure to ensure a connection.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('transfers','transfer_type','3','Not Possible','Transfers are not possible between routes at this location.');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','100','Railway Service','Railway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','101','High Speed Rail Service','High Speed Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','102','Long Distance Trains','Long Distance Trains');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','103','Inter Regional Rail Service','Inter Regional Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','104','Car Transport Rail Service','Car Transport Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','105','Sleeper Rail Service','Sleeper Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','106','Regional Rail Service','Regional Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','107','Tourist Railway Service','Tourist Railway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','108','Rail Shuttle (Within Complex)','Rail Shuttle (Within Complex)');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','109','Suburban Railway','Suburban Railway');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','110','Replacement Rail Service','Replacement Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','111','Special Rail Service','Special Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','112','Lorry Transport Rail Service','Lorry Transport Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','113','All Rail Services','All Rail Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','114','Cross-Country Rail Service','Cross-Country Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','115','Vehicle Transport Rail Service','Vehicle Transport Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','116','Rack and Pinion Railway','Rack and Pinion Railway');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','117','Additional Rail Service','Additional Rail Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','200','Coach Service','Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','201','International Coach Service','International Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','202','National Coach Service','National Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','203','Shuttle Coach Service','Shuttle Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','204','Regional Coach Service','Regional Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','205','Special Coach Service','Special Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','206','Sightseeing Coach Service','Sightseeing Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','207','Tourist Coach Service','Tourist Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','208','Commuter Coach Service','Commuter Coach Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','209','All Coach Services','All Coach Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','300','Suburban Railway Service','Suburban Railway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','400','Urban Railway Service','Urban Railway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','401','Metro Service','Metro Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','402','Underground Service','Underground Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','403','Urban Railway Service','Urban Railway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','404','All Urban Railway Services','All Urban Railway Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','405','Monorail','Monorail');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','500','Metro Service','Metro Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','600','Underground Service','Underground Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','700','Bus Service','Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','701','Regional Bus Service','Regional Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','702','Express Bus Service','Express Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','703','Stopping Bus Service','Stopping Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','704','Local Bus Service','Local Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','705','Night Bus Service','Night Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','706','Post Bus Service','Post Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','707','Special Needs Bus','Special Needs Bus');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','708','Mobility Bus Service','Mobility Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','709','Mobility Bus for Registered Disabled','Mobility Bus for Registered Disabled');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','710','Sightseeing Bus','Sightseeing Bus');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','711','Shuttle Bus','Shuttle Bus');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','712','School Bus','School Bus');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','713','School and Public Service Bus','School and Public Service Bus');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','714','Rail Replacement Bus Service','Rail Replacement Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','715','Demand and Response Bus Service','Demand and Response Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','716','All Bus Services','All Bus Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','717','Share Taxi Service','Share Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','800','Trolleybus Service','Trolleybus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','900','Tram Service','Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','901','City Tram Service','City Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','902','Local Tram Service','Local Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','903','Regional Tram Service','Regional Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','904','Sightseeing Tram Service','Sightseeing Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','905','Shuttle Tram Service','Shuttle Tram Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','906','All Tram Services','All Tram Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','907','Cable Tram','Cable Tram');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1000','Water Transport Service','Water Transport Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1001','International Car Ferry Service','International Car Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1002','National Car Ferry Service','National Car Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1003','Regional Car Ferry Service','Regional Car Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1004','Local Car Ferry Service','Local Car Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1005','International Passenger Ferry Service','International Passenger Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1006','National Passenger Ferry Service','National Passenger Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1007','Regional Passenger Ferry Service','Regional Passenger Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1008','Local Passenger Ferry Service','Local Passenger Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1009','Post Boat Service','Post Boat Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1010','Train Ferry Service','Train Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1011','Road-Link Ferry Service','Road-Link Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1012','Airport-Link Ferry Service','Airport-Link Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1013','Car High-Speed Ferry Service','Car High-Speed Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1014','Passenger High-Speed Ferry Service','Passenger High-Speed Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1015','Sightseeing Boat Service','Sightseeing Boat Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1016','School Boat','School Boat');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1017','Cable-Drawn Boat Service','Cable-Drawn Boat Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1018','River Bus Service','River Bus Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1019','Scheduled Ferry Service','Scheduled Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1020','Shuttle Ferry Service','Shuttle Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1021','All Water Transport Services','All Water Transport Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1100','Air Service','Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1101','International Air Service','International Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1102','Domestic Air Service','Domestic Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1103','Intercontinental Air Service','Intercontinental Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1104','Domestic Scheduled Air Service','Domestic Scheduled Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1105','Shuttle Air Service','Shuttle Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1106','Intercontinental Charter Air Service','Intercontinental Charter Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1107','International Charter Air Service','International Charter Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1108','Round-Trip Charter Air Service','Round-Trip Charter Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1109','Sightseeing Air Service','Sightseeing Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1110','Helicopter Air Service','Helicopter Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1111','Domestic Charter Air Service','Domestic Charter Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1112','Schengen-Area Air Service','Schengen-Area Air Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1113','Airship Service','Airship Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1114','All Air Services','All Air Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1200','Ferry Service','Ferry Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1300','Aerial Lift Service','Aerial Lift Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1301','Telecabin Service','Telecabin Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1302','Aerial Tramway Service','Aerial Tramway Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1303','Elevator Service','Elevator Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1304','Chair Lift Service','Chair Lift Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1305','Drag Lift Service','Drag Lift Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1306','Small Telecabin Service','Small Telecabin Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1307','All Telecabin Services','All Telecabin Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1400','Funicular Service','Funicular Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1401','Funicular Service','Funicular Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1402','All Funicular Service','All Funicular Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1500','Taxi Service','Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1501','Communal Taxi Service','Communal Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1502','Water Taxi Service','Water Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1503','Rail Taxi Service','Rail Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1504','Bike Taxi Service','Bike Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1505','Licensed Taxi Service','Licensed Taxi Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1506','Private Hire Service Vehicle','Private Hire Service Vehicle');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1507','All Taxi Services','All Taxi Services');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1600','Self Drive','Self Drive');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1601','Hire Car','Hire Car');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1602','Hire Van','Hire Van');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1603','Hire Motorbike','Hire Motorbike');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1604','Hire Cycle','Hire Cycle');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1700','Miscellaneous Service','Miscellaneous Service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','0','Tram, Streetcar, Light rail','Any light rail or street level system within a metropolitan area');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','1','Subway, Metro','Any underground rail system within a metropolitan area');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','2','Rail','Used for intercity or long-distance travel');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','3','Bus','Used for short- and long-distance bus routes');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','4','Ferry','Used for short- and long-distance boat service');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','5','Cable tram','Used for street-level rail cars where the cable runs beneath the vehicle, e.g., cable car in San Francisco');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','6','Aerial lift, suspended cable car (e.g., gondola lift, aerial tramway)','Cable transport where cabins, cars, gondolas or open chairs are suspended by means of one or more cables');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','7','Funicular','Any rail system designed for steep inclines');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','11','Trolleybus','Electric buses that draw power from overhead wires using poles');
INSERT INTO ENUM_VALUES(FILE, FIELD, CODE, NAME, DESCRIPTION) VALUES ('routes','route_type','12','Monorail','Railway in which the track consists of a single rail or a beam');

DROP TABLE IF EXISTS `AGENCIES`;
CREATE TABLE `AGENCIES` (
    ID                  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    AGENCY_ID           VARCHAR(100) NOT NULL,
    NAME                VARCHAR(255) NOT NULL,
    URL                 VARCHAR(255) NOT NULL,
    TIMEZONE            VARCHAR(100) NOT NULL,
    LANGUAGE            VARCHAR(100),
    PHONE               VARCHAR(100),
    FARE_URL            VARCHAR(100),
    EMAIL               VARCHAR(100)
);

DROP TABLE IF EXISTS `STOPS`;
CREATE TABLE `STOPS` (
    ID                  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    STOP_ID             VARCHAR(255),
    CODE                VARCHAR(50),
    NAME                VARCHAR(255) NOT NULL,
    DESCRIPTION         VARCHAR(255),
    LATITUDE            DECIMAL(10,6) NOT NULL,
    LONGITUDE           DECIMAL(10,6) NOT NULL,
    ZONE_ID             VARCHAR(255),
    URL                 VARCHAR(255),
    LOCATION_TYPE       VARCHAR(50),
    PARENT_STATION      VARCHAR(100),
    TIMEZONE            VARCHAR(50),
    WHEELCHAIR_BOARDING VARCHAR(50),
    LEVEL_ID            VARCHAR(100),
    PLATFORM_CODE       VARCHAR(100)
);

DROP TABLE IF EXISTS `ROUTES`;
CREATE TABLE `ROUTES` (
    ID                  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ROUTE_ID            VARCHAR(100),
    AGENCY_ID           VARCHAR(50),
    SHORT_NAME          VARCHAR(50) NOT NULL,
    LONG_NAME           VARCHAR(255) NOT NULL,
    DESCRIPTION         VARCHAR(255),
    ROUTE_TYPE          INT(10) NOT NULL,
    TEXT_COLOR          VARCHAR(255),
    ROUTE_COLOR         VARCHAR(255),
    URL                 VARCHAR(255),
    SORT_ORDER          INT(10)
);

DROP TABLE IF EXISTS `TRIPS`;
CREATE TABLE `TRIPS` (
    ID                      BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ROUTE_ID                VARCHAR(100) NOT NULL,
    SERVICE_ID              VARCHAR(100) NOT NULL,
    TRIP_ID                 VARCHAR(255) NOT NULL,
    HEADSIGN                VARCHAR(50),
    SHORT_NAME              VARCHAR(100),
    DIRECTION_ID            VARCHAR(50),
    BLOCK_ID                VARCHAR(100),
    SHAPE_ID                VARCHAR(100),
    WHEELCHAIR_ACCESSIBLE   VARCHAR(50),
    BIKES_ALLOWED           VARCHAR(50)
);

DROP TABLE IF EXISTS `CALENDARS`;
CREATE TABLE `CALENDARS` (
    ID                  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SERVICE_ID          VARCHAR(255) NOT NULL,
    MONDAY              TINYINT(1) NOT NULL,
    TUESDAY             TINYINT(1) NOT NULL,
    WEDNESDAY           TINYINT(1) NOT NULL,
    THURSDAY            TINYINT(1) NOT NULL,
    FRIDAY              TINYINT(1) NOT NULL,
    SATURDAY            TINYINT(1) NOT NULL,
    SUNDAY              TINYINT(1) NOT NULL,
    START_DATE          VARCHAR(8),
    END_DATE            VARCHAR(8)
);

DROP TABLE IF EXISTS `STOPTIMES`;
CREATE TABLE `STOPTIMES` (
    ID                      BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TRIP_ID                 VARCHAR(100) NOT NULL,
    ARRIVAL_TIME            VARCHAR(8) NOT NULL,
    DEPARTURE_TIME          VARCHAR(8) NOT NULL,
    STOP_ID                 VARCHAR(100) NOT NULL,
    SEQUENCE                BIGINT NOT NULL,
    HEADSIGN                VARCHAR(50),
    PICKUP_TYPE             VARCHAR(2),
    DROP_OFF_TYPE           VARCHAR(2),
    SHAPE_DISTANCE_TRAVELED DECIMAL(10,6),
    TIMEPOINT               VARCHAR(50)
);

DROP TABLE IF EXISTS `FREQUENCIES`;
CREATE TABLE `FREQUENCIES` (
    ID                      BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TRIP_ID                 VARCHAR(100) NOT NULL,
    START_TIME              VARCHAR(8) NOT NULL,
    END_TIME                VARCHAR(8) NOT NULL,
    HEADWAY_SECS            BIGINT NOT NULL,
    EXACT_TIMES             VARCHAR(2)
);
