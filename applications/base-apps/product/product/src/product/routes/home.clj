(ns product.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string parse-string]]))

(def products (atom {"1" {:name "John" :color "A"}
                      "2" {:name "Jane" :color "B"}
                      "3" {:name "Anne" :color "C"}
                      }))


(defresource home
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @products))
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource get-product
  :allowed-methods [:get]
  :handle-ok (fn [context]
         (let [params (get-in context [:request :form-params])]
             (let [obj  (get params "product-id")]
               (generate-string (@products obj)))))
  :available-media-types ["application/json"])

(defresource add-product
  :allowed-methods [:post]
  :post! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "product"))]
               (swap! products assoc (first (first obj)) (second (first obj)))
               (println products))))
  :available-media-types ["application/json"])

(defresource update-product
  :allowed-methods [:put]
  :put! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "product"))]
               (swap! products assoc (first (first obj)) (second (first obj)))
               (println products))))
  :available-media-types ["application/json"])

(defresource delete-product
  :allowed-methods [:delete]
  :delete! (fn [context]
             (let [params (get-in context[:request :form-params])]
               (let [obj (parse-string (get params "product-id"))]
                 (swap! products dissoc (first (first obj)))
                 (println products))))
  :available-media-types ["application/json"])





(defroutes home-routes
  (ANY "/" request home)
  (ANY "/get-product" request get-product)
  (ANY "/add-product" request add-product)
  (ANY "/update-product" request update-product)
  (ANY "/delete-product" request delete-product))
