(ns employee.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string parse-string]]))

(def employees (atom {1 {:first-name "John" :last-name "A"}
                      2 {:first-name "Jane" :last-name "B"}
                      3 {:first-name "Anne" :last-name "C"}
                      }))

(defresource home
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @employees))
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource add-user
  :allowed-methods [:post]
  :post! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "user"))]
               (println (first obj) " " (second obj))
               (swap! employees assoc (first obj) (second obj))
               (println employees))))
  :available-media-types ["application/json"])

(defroutes home-routes
  (ANY "/" request home)
  (ANY "/add-user" request add-user))
