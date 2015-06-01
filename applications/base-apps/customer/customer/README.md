# customer


## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server


## Services
Add a customer:
curl -X POST localhost:3000/add-customer --data 'customer={"5" : {"first-name": "Bertil"}}'

Get all customers:
curl -X GET localhost:3000

Get a specific employee:
curl -X GET localhost:3000/get-customer --data 'customer-id=5'

Update a customer:
curl -X PUT localhost:3000/update-customer --data 'customer={"5" : {"first-name": "Bertil", "last-name": "ll"}}'

Delete a customer:
curl -X DELETE localhost:3000/delete-customer --data 'customer-id=5'

## Docker deployment

From the root of this project

sudo docker pull clojure
sudo docker build -t customer .
sudo docker run -it --rm --name running-app customer
