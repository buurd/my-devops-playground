(ns order-aggregate.routes.home
  (:require [compojure.core :refer :all]
            [order-aggregate.views.layout :as layout]
            [clj-http.client :as client]))

(defn home []
  (println (client/get "http://www.google.com"))
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))
