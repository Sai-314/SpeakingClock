Speaking clock
==============
Specification ------
1. Given a 24-hour clock convert the current time into words e.g. "08:34" should be converted to "It's eight thirty four"
2. Make another controller to handle input from user in the format “11:25”
3. Return Midday and Midnight as "It's Midday and "It's Midnight"

Endpoints
==============
Swagger Link :- http://localhost:8081/swagger-ui/index.html#/

Endpoint to convert time into words :- http://localhost:8081/speakingClock/{time}

FOR eg. :- http://localhost:8081/speakingClock/08:34

Endpoint to tell It is Mid-day, Mid-night or none :- http://localhost:8081/speakingClock/dayType/{time}

For eg. :- http://localhost:8081/speakingClock/dayType/01:44
