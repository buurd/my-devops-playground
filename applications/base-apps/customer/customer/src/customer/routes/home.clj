(ns customer.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string parse-string]]))

(def customers (atom {"1" {:first-name "John" :last-name "A"}
                      "2" {:first-name "Jane" :last-name "B"}
                      "3" {:first-name "Anne" :last-name "C"}
                      }))


(defresource home
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @customers))
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource get-customer
  :allowed-methods [:get]
  :handle-ok (fn [context]
         (let [params (get-in context [:request :form-params])]
             (let [obj  (get params "customer-id")]
               (generate-string (@customers obj)))))
  :available-media-types ["application/json"])

(defresource add-customer
  :allowed-methods [:post]
  :post! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "customer"))]
               (swap! customers assoc (first (first obj)) (second (first obj))))))
  :available-media-types ["application/json"])

(defresource update-customer
  :allowed-methods [:put]
  :put! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "customer"))]
               (swap! customers assoc (first (first obj)) (second (first obj))))))
  :available-media-types ["application/json"])

(defresource delete-customer
  :allowed-methods [:delete]
  :delete! (fn [context]
             (let [params (get-in context[:request :form-params])]
               (let [obj (parse-string (get params "customer-id"))]
                 (swap! customers dissoc (first (first obj))))))
  :available-media-types ["application/json"])


(defroutes home-routes
  (ANY "/" request home)
  (ANY "/get-customer" request get-customer)
  (ANY "/add-customer" request add-customer)
  (ANY "/update-customer" request update-customer)
  (ANY "/delete-customer" request delete-customer))
