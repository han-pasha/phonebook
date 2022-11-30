# HOW TO TEST THE API

## NOTES :
- THERE IS DATA THAT WOULD AUTOMATICALLY CREATED BY THE SERVICE EACH TIME THE PROGRAM IS START.
- THE REASON WHY IM NOT USING DATA.SQL TO POPULATE THE DATA IS BECAUSE ID AND AUDIT FIELD WONT BE CREATED AUTOMATICALLY WHEN USING DATA.SQL.
- FOR SCRIPT TESTING, PLEASE RUN ALL THE TEST IN TEST FOLDER.
- THE DATABASE THAT BEING USED RIGHT NOW IS H2. SO IT WOULD BE EASY TO USE WITHOUT TO INSTALL ANYTHING.

## ADD
There is only 2 value needed for adding new phone.    
First is name and second is phoneNumber.  
Others information like id, createdDate, createdBy, etc. is created automatically.   
Name had range from 3-32.   
phoneNumber has a regular expression to check if it's valid.
- Endpoint : http://localhost:8010/phone/add
- Payload : {
  "name":"test",
  "phoneNumber":"082222222222"
  }

## GET
GET DOESN'T HAVE ANY PAYLOAD BESIDES ITS QUERY PARAM.

### GET ALL
- Endpoint : http://localhost:8010/phone/get-all

### GET ALL WITH SPECIFIC PAGE
- Endpoint : http://localhost:8010/phone/get-all?pageNo=1&pageSize=3

### GET ALL WITH SORT
- Endpoint : http://localhost:8010/phone/get-all?sortBy=name&direction=asc (default)

### GET ALL WITH CONTAINS NAME
- Endpoint : http://localhost:8010/phone/get-all?name=astuti | optional &pageNo=1&pageSize=3

### GET ALL WITH CONTAINS NUMBER
- Endpoint : http://localhost:8010/phone/get-all?phoneNo=081111111111 | optional &pageNo=1&pageSize=3

### GET ONE
Get one is using the same api as the getAll,  
This is because the filter methods in getAll could also return a single value;
- Endpoint : http://localhost:8010/phone/get-all?phoneNo=082222222222
- Endpoint : http://localhost:8010/phone/get-all?name=astuti

## UPDATE
Update is based on phoneNumber. It's because the phoneNumber is unique
it would be impossible to give an id as param without ui.  
- Endpoint : http://localhost:8010/phone/update
- Payload : {
  "name":"test",
  "phoneNumber":"082329365425"
  }

## DELETE
Update is based on phoneNumber. It's because the phoneNumber is unique
- Endpoint : http://localhost:8010/phone/delete/{phoneNumber}
