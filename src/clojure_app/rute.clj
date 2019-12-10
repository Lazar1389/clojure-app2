(ns clojure-app.rute
(:require [hiccup.core :refer :all]
          [hiccup.page :refer :all]
          )

  )

(defn zbogom
   "Testiranje ruta, html5 generise celu stranicu "
   [request]
   (html5 {:lang "en"}
          [:head (include-js "myscript.js") (include-css "mystyle.css")]
          [:body
           [:div [:h1 {:class "info"} "Heading 1"]]
           [:div [:p "Paragraf 1"]]
           [:div [:p "Paragraf 2"]]
           [:div [:p "Paragraf 3"]]
           [:div [:p "Paragraf 4"]]]))

(defn hello
  "Query string parametri"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Zdravo " name ".  Iz query stringa")
     :headers {}}))

(defn about
  "Informacije o aplikaciji"
  [request]
  {:status 200
   :headers {}
   :body "Informacija.."})

(defn welcome
  "Osnovna putanja localhost:8005"
  [request]
  (html [:h1 "Zdravo, Clojure "]
        [:p "Dobrodosli x"]))