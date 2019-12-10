(ns clojure-app.core
  (:require [ring.adapter.jetty :as webserver]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [clojure-app.rute :refer :all]
            )
  )


(defroutes app
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






(-dev-main 8005)
