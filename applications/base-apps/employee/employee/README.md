# employee


## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server


## Services
Add a user:
curl -X POST localhost:3000/add-user --data 'user={"5" : {"first-name": "Bertil"}}'

Get all users:
curl -X GET localhost:3000

Get a specific user:
curl -X GET localhost:3000/get-user --data 'user-id=5'

Update a user:
curl -X PUT localhost:3000/update-user --data 'user={"5" : {"first-name": "Bertil", "last-name": "ll"}}'

Delete a user:
curl -X DELETE localhost:3000/delete-user --data 'user-id=5'

## Docker deployment

From the root of this project

sudo docker build -t employee .
sudo docker run -it --rm --name running-app employee
