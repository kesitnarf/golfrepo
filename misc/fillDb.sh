#!/usr/bin/env bash

# Source 1

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentId": "174638",
  "tournamentName": "Women'\''s Open Championship",
  "forecast": "fair",
  "courseName": "Sunnydale Golf Course",
  "countryCode": "GB",
  "startDate": "09/07/21",
  "endDate": "13/07/21",
  "roundCount": "4"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentId": "180000",
  "tournamentName": "The Canuckistan Classic",
  "forecast": "rainy",
  "courseName": "Augusta National",
  "countryCode": "US",
  "startDate": "01/08/21",
  "endDate": "08/08/21",
  "roundCount": "7"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentId": "190000",
  "tournamentName": "The Manitoba Meltdown",
  "forecast": "sunny",
  "courseName": "Royal Melbourne G.C.",
  "countryCode": "AU",
  "startDate": "21/08/21",
  "endDate": "29/08/21",
  "roundCount": "8"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentId": "200000",
  "tournamentName": "The Beer Open",
  "forecast": "snow",
  "courseName": "Cape Kidnappers G. Cse.",
  "countryCode": "NZ",
  "startDate": "01/09/21",
  "endDate": "04/09/21",
  "roundCount": "3"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE:SOURCE_1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentId": "210000",
  "tournamentName": "The Cartgirl Invitational",
  "forecast": "drizzle",
  "courseName": "Kingston Heath G.C.",
  "countryCode": "AU",
  "startDate": "11/09/21",
  "endDate": "12/09/21",
  "roundCount": "2"
}'

# Source 2

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentUUID": "southWestInvitational",
  "golfCourse": "Happy Days Golf Club",
  "competitionName": "South West Invitational",
  "hostCountry": "United States Of America",
  "epochStart": "1638349200",
  "epochFinish": "1638468000",
  "rounds": "2",
  "playerCount": "35"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentUUID": "theInebriatedWalkaboutAnnual",
  "golfCourse": "Cabot Cliffs",
  "competitionName": "The Inebriated Walkabout Annual",
  "hostCountry": "Canada",
  "epochStart": "1634896800",
  "epochFinish": "1635184800",
  "rounds": "4",
  "playerCount": "16"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentUUID": "bombedAndGougingChampionship",
  "golfCourse": "Golf de Morfontaine",
  "competitionName": "Bombed and Gouging Championship",
  "hostCountry": "France",
  "epochStart": "1635757200",
  "epochFinish": "1636657200",
  "rounds": "10",
  "playerCount": "128"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentUUID": "theGolfswingMemorial",
  "golfCourse": "Hirono G.C.",
  "competitionName": "The Golfswing Memorial",
  "hostCountry": "Japan",
  "epochStart": "1637398800",
  "epochFinish": "1637442000",
  "rounds": "1",
  "playerCount": "4"
}'

curl --silent --location --request POST 'http://localhost:8081/tournaments/' \
--header 'SOURCE: SOURCE_2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "tournamentUUID": "theBarstoolQualifier",
  "golfCourse": "Shanqin Bay G.C.",
  "competitionName": "The Barstool Qualifier",
  "hostCountry": "China",
  "epochStart": "1638262800",
  "epochFinish": "1638471600",
  "rounds": "3",
  "playerCount": "24"
}'
