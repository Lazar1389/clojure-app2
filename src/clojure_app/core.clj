(ns clojure-app.core
  (:require [ring.adapter.jetty :as webserver]
            [ring.middleware.reload :refer [wrap-reload]])
  )

(defn welcome
  "A ring handler to process all requests for the web server.  If a request is for something other than then an error message is returned"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure World</h1>
            <p>Test.</p>"
     :headers {}}
    {:status 404
     :body "<h1>Nedostupna ruta</h1>
            <p>></p>"
     :headers {}}))


(defn -dev-main
  "Jednostavna web aplikacija koristi Ring i Jetty, dodat middleware wrap--reload za automatsko prepoznavanje promena u kodu"
  [port-number]
  (webserver/run-jetty
    (wrap-reload #'welcome)
    {:port  (Integer. port-number)
     :join? false}))

(defn main
  "Ovde nema automatskog detektovanja promene koda"
  [port-number]
  (webserver/run-jetty
    welcome
    {:port  (Integer. port-number)}))



(-dev-main 8000)
