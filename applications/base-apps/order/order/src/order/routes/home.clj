(ns order.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string parse-string]]))

(def orders (atom {"1" {:customer-id 1 :employee-id 1 :order-rows []}
                      "2" {:customer-id 2 :employee-id 1 :order-rows []}
                      "3" {:customer-id 3 :employee-id 2 :order-rows []}
                      }))


(defresource home
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @orders))
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource get-order
  :allowed-methods [:get]
  :handle-ok (fn [context]
         (let [params (get-in context [:request :form-params])]
             (let [obj  (get params "order-id")]
               (generate-string (@orders obj)))))
  :available-media-types ["application/json"])

(defresource add-order
  :allowed-methods [:post]
  :post! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "order"))]
               (swap! orders assoc (first (first obj)) (second (first obj)))
               (println orders))))
  :available-media-types ["application/json"])

(defresource update-order
  :allowed-methods [:put]
  :put! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "order"))]
               (swap! orders assoc (first (first obj)) (second (first obj)))
               (println orders))))
  :available-media-types ["application/json"])

(defresource delete-order
  :allowed-methods [:delete]
  :delete! (fn [context]
             (let [params (get-in context[:request :form-params])]
               (let [obj (parse-string (get params "order-id"))]
                 (swap! orders dissoc (first (first obj)))
                 (println orders))))
  :available-media-types ["application/json"])


(defroutes home-routes
  (ANY "/" request home)
  (ANY "/get-order" request get-order)
  (ANY "/add-order" request add-order)
  (ANY "/update-order" request update-order)
  (ANY "/delete-order" request delete-order))
