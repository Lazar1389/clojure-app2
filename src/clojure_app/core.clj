(ns clojure-app.core
  (:require [ring.adapter.jetty :as webserver]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer :all]
            [compojure.route :refer [not-found]]
            [ring.middleware.params :refer :all]
            [ring.handler.dump :refer [handle-dump]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.content-type :refer :all]
            [clojure-app.rute :refer :all]
            [clojure.java.jdbc :as jdbc]
            [clojure-app.myview :as v ]
            [clojure-app.views :as view ]
            [clojure-app.db :as db ]
            [ring.middleware.json  :refer [wrap-json-response]]
            [ring.util.response  :refer [response]]
            )
  )


(defroutes app
           (GET "/detalji/:is-id" [is-id] (view/detalji-is is-id))
           (GET "/pocetna" req (view/pocetna req))
           (GET "/db/selectall" req (view/svi-is req))
           (GET "/obrisi/:is-id" [is-id] (view/obrisi-zapis is-id))
           (GET "/dodaj-novi" [req]
             (view/dodaj-novi-view req))
           (POST "/dodaj-novi"
                 {params :params}
             (view/Dodaj-novi-is-rezultat params))
           (GET "/" [] welcome)
           (GET "/zbogom" [] zbogom)
           (GET "/about"   [] about)
           (GET "/hello/:name" [] hello)
           (not-found "<h1>Грешка</h1>")

           )





(defn test
       [port-number]
  ()
       (webserver/run-jetty (wrap-defaults #'app site-defaults)
                            {:port  (Integer. port-number)}))




(test 8006)
