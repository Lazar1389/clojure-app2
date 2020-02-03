(ns clojure-app.views
  (:require [clojure-app.db :as db]
            [hiccup.page :as page]
            [ring.middleware.json :as json]
            [ring.util.anti-forgery :as util]
            )
  )
(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (page/include-css "/css/styles.css")])

(def heder
  [:div#heder
   "[ "
   [:a {:href "/pocetna"} "Почетна"]
   " | "
   [:a {:href "/db/selectall"} "Сви информациони системи"]
   " | "
   [:a {:href "/dodaj-novi"} "Додај нови ИС"]
   " ]"])
(defn add-location-page
  [req]
  (page/html5
    (gen-page-head "Dodaj novi zapis")
    heder
    req

    [:h1 "Додај нови запис"]
    [:form {:action "/dodaj-novi" :method "POST"}
     (util/anti-forgery-field)
     [:p "NazivIS value: " [:input {:type "text" :name "NazivIS"}]]
     [:p "FazaZivotnogCiklusa value: " [:td  [:select {:name "FazaZivotnogCiklusa"}
                                              [:option {:value "У развоју"} "У развоју"]
                                              [:option {:value "У употреби"} "У употреби"]
                                              [:option {:value "Ван употребе"} "Ван употребе"]
                                              [:option {:value "Модификација"} "Модификација"]]]]
     [:p "Oblast value: " [:input {:type "text" :name "Oblast"}]]
     [:p "Tip value: " [:input {:type "text" :name "Tip"}]]
     [:p "Nosilac value: " [:input {:type "text" :name "Nosilac"}]]
     [:p "OperativniSistem value: " [:input {:type "text" :name "OperativniSistem"}]]

     [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{ :keys [NazivIS FazaZivotnogCiklusa Oblast Tip Nosilac OperativniSistem]}]
  (let [id (db/dodaj-novi NazivIS FazaZivotnogCiklusa Oblast Tip Nosilac OperativniSistem)]
    (page/html5
      (gen-page-head "Додата локација")
      heder
      [:h1 "Додата локација"]
      [:p "Успешно додат ИС - "  NazivIS ]
       [:a {:href (str "/detalji/" (:generated_key id))} "Види детаље"]
       )))

(defn obrisi-zapis
  [request]
(page/html5
  (gen-page-head "Obrisan zapis")
  heder
            (let [x (db/obrisi request)]
              [:h1 (str "Успешно сте обрисали запис чији је ID: " request) ]
            )
  [:a {:href "/db/selectall"} "Повратак на листу ИС"]
  ) )





(defn svi-is
  [req]
  (let [all-rec (db/select-all req)]
    (page/html5
      (gen-page-head "Сви информациони системи")
      heder
      [:h1 "Сви информациони системи"]
      [:table
       [:tr [:th "Назив ИС"] [:th "Фаза животног циклуса"] [:th "Област"] [:th "Тип"] [:th "Носилац"] [:th "Оперативни систем"] [:th "B"] [:th "D"]]
       (for [zapis all-rec]

         [:tr [:td (:nazivis zapis)] [:td (:fazazivotnogciklusa zapis)] [:td (:oblast zapis)] [:td (:tip zapis)] [:td (:nosilac zapis)] [:td (:operativnisistem  zapis)] [:td        [:a {:href (str "/detalji/" (:id  zapis)) } "Детаљи"] ] [:td        [:a {:href (str "/obrisi/" (:id  zapis)) } "Обриши запис"] ]  ])])))

(defn detalji-is
  [is-id]
  (let [{nazivIs :nazivis tip :tip faza :fazazivotnogciklusa oblast :oblast nosilac :nosilac opsistem :operativnisistem} (db/get-is is-id)]
    (page/html5
      (gen-page-head (str "Location " is-id))
      heder
      [:h1 "Информациони систем:"]
      [:p "Ид: " is-id]
      [:p "Назив ИС: " nazivIs]
      [:p "Област: " oblast]
      [:p "Носилац: " nosilac]
      [:p "Оперативни систем: " opsistem]

      [:p "Тип: " tip])))



(defn home-page
  [req]
  (page/html5
    (gen-page-head "Home")
    heder
    [:h1 "Home"]
    [:p "Webapp to store and display some 2D (x,y) locations."]))




(comment

(ns my-webapp.views
  (:require [my-webapp.db :as db]
            [clojure.string :as str]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (page/include-css "/css/styles.css")])

(def heder
  [:div#heder
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-location"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View All Locations"]
   " ]"])

(defn home-page
  []
  (page/html5
    (gen-page-head "Home")
    heder
    [:h1 "Home"]
    [:p "Webapp to store and display some 2D (x,y) locations."]))

(defn add-location-page
  []
  (page/html5
    (gen-page-head "Add a Location")
    heder
    [:h1 "Add a Location"]
    [:form {:action "/add-location" :method "POST"}
     (util/anti-forgery-field)                              ; prevents cross-site scripting attacks
     [:p "x value: " [:input {:type "text" :name "x"}]]
     [:p "y value: " [:input {:type "text" :name "y"}]]
     [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{:keys [x y]}]
  (let [id (db/add-location-to-db x y)]
    (page/html5
      (gen-page-head "Added a Location")
      heder
      [:h1 "Added a Location"]
      [:p "Added [" x ", " y "] (id: " id ") to the db. "
       [:a {:href (str "/location/" id)} "See for yourself"]
       "."])))

(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (page/html5
      (gen-page-head (str "Location " loc-id))
      heder
      [:h1 "A Single Location"]
      [:p "id: " loc-id]
      [:p "x: " x]
      [:p "y: " y])))

(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (page/html5
      (gen-page-head "All Locations in the db")
      heder
      [:h1 "All Locations"]
      [:table
       [:tr [:th "id"] [:th "x"] [:th "y"]]
       (for [loc all-locs]
         [:tr [:td (:id loc)] [:td (:x loc)] [:td (:y loc)]])])))

)

