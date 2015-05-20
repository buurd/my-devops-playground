(ns employee.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string parse-string]]))

(def employees (atom {"1" {:first-name "John" :last-name "A"}
                      "2" {:first-name "Jane" :last-name "B"}
                      "3" {:first-name "Anne" :last-name "C"}
                      }))


(defresource home
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @employees))
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource get-employee
  :allowed-methods [:get]
  :handle-ok (fn [context]
         (let [params (get-in context [:request :form-params])]
             (let [obj  (get params "employee-id")]
               (generate-string (@employees obj)))))
  :available-media-types ["application/json"])

(defresource add-employee
  :allowed-methods [:post]
  :post! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "employee"))]
               (swap! employees assoc (first (first obj)) (second (first obj)))
               (println employees))))
  :available-media-types ["application/json"])

(defresource update-employee
  :allowed-methods [:put]
  :put! (fn [context]
           (let [params (get-in context [:request :form-params])]
             (let [obj (parse-string (get params "employee"))]
               (swap! employees assoc (first (first obj)) (second (first obj)))
               (println employees))))
  :available-media-types ["application/json"])

(defresource delete-employee
  :allowed-methods [:delete]
  :delete! (fn [context]
             (let [params (get-in context[:request :form-params])]
               (let [obj (parse-string (get params "employee-id"))]
                 (swap! employees dissoc (first (first obj)))
                 (println employees))))
  :available-media-types ["application/json"])





(defroutes home-routes
  (ANY "/" request home)
  (ANY "/get-employee" request get-employee)
  (ANY "/add-employee" request add-employee)
  (ANY "/update-employee" request update-employee)
  (ANY "/delete-employee" request delete-employee))
