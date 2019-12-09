(ns clojure-app.core
  (:require [ring.adapter.jetty :as webserver]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]

            )
  )
(defn goodbye
  "Goodbye"
  [request]
  {:status 200
   :headers {}
   :body "<h1>Zbogom</h1>"})

(defn about
  "Informacije o aplikaciji"
  [request]
  {:status 200
   :headers {}
   :body "Informacija.."})

(defn welcome
  "A ring handler to process all requests for the web server.  If a request is for something other than then an error message is returned"
  [request]
  {:status 200
     :body "<h1>Zdravoo, Clojure World</h1>
            <p>Test.2</p>"
     :headers {}})

(defroutes app
           (GET "/" [] welcome)
           (GET "/zbogom" [] goodbye)
           (GET "/about"   [] about)
           (GET "/request-info" [] handle-dump)
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
