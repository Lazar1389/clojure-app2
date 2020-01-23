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
           (GET "/pocetna" req (view/home-page req))
           (GET "/db/selectall" req (view/svi-zaposleni req))
           (GET "/add-location" [req]
             (view/add-location-page req))
           (POST "/add-location"
                 {params :params}
             (view/add-location-results-page params))
           (GET "/" req (v/main req))
           (GET "/get-form.html" req (v/get-form req))
           (GET "/post-form.html" req (v/post-form req))
           (GET "/get-submit" req (v/display-result req))
           (POST "/post-submit" req (v/display-result req))
           (GET "/" [] welcome)
           (GET "/zbogom" [] zbogom)
           (GET "/about"   [] about)
           (GET "/request-info" [] handle-dump)
           (GET "/hello/:name" [] hello)
           (not-found " <h1>greska</h1>")

           )


(defn -dev-main
  "Jednostavna web aplikacija koristi Ring i Jetty, dodat je middleware wrap- -reload za automatsko prepoznavanje promena u kodu"
  [port-number]
  (webserver/run-jetty (wrap-reload #'app)
    {:port  (Integer. port-number)}))

(defn main
  "Ovde nema automatskog detektovanjaa promenee koda"
  [port-number]
  (webserver/run-jetty
    app
    {:port  (Integer. port-number)}))



(defn test
       [port-number]
  ()
       (webserver/run-jetty (wrap-defaults #'app site-defaults)
                            {:port  (Integer. port-number)})


  )




(test 8006)
