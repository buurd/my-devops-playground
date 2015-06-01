# employee


## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server


## Services
Add an empoloyee:
curl -X POST localhost:3000/add-employee --data 'employee={"5" : {"first-name": "Bertil"}}'

Get all employees:
curl -X GET localhost:3000

Get a specific employee:
curl -X GET localhost:3000/get-employee --data 'employee-id=5'

Update an employee:
curl -X PUT localhost:3000/update-employee --data 'employee={"5" : {"first-name": "Bertil", "last-name": "ll"}}'

Delete an employee:
curl -X DELETE localhost:3000/delete-employee --data 'employee-id=5'

## Docker deployment

From the root of this project

sudo docker pull clojure
sudo docker build -t employee .
sudo docker run -it --rm --name running-app employee
