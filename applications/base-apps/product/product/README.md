# product


## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server


## Services
Add an product:
curl -X POST localhost:3000/add-product --data 'product={"5" : {"name": "Bertil"}}'

Get all products
curl -X GET localhost:3000

Get a specific product:
curl -X GET localhost:3000/get-product --data 'product-id=5'

Update an product:
curl -X PUT localhost:3000/update-product --data 'product={"5" : {"name": "Bertil", "color": "yellow"}}'

Delete an product:
curl -X DELETE localhost:3000/delete-employee --data 'product-id=5'

## Docker deployment

From the root of this project

sudo docker build -t product .
sudo docker run -it --rm --name running-app product
