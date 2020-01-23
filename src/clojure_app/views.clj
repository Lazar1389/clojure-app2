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

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/db/selectall"} "Svi zapisi"]
   " | "
   [:a {:href "/all-locations"} "View All Locations"]
   " ]"])
(defn add-location-page
  [req]
  (page/html5
    (gen-page-head "Dodaj novi zapis")
    header-links
    req

    [:h1 "Dodaj novi zapis"]
    [:form {:action "/add-location" :method "POST"}
     (util/anti-forgery-field)
     [:p "ime value: " [:input {:type "text" :name "ime"}]]
     [:p "prezime value: " [:input {:type "text" :name "prezime"}]]
     [:p "jmbg value: " [:input {:type "text" :name "jmbg"}]]
     [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{ :keys [ime prezime jmbg]}]
  (let [id (db/dodaj-novi ime prezime jmbg)]
    (page/html5
      (gen-page-head "Added a Location")
      header-links
      [:h1 "Added a Location"]
      [:p "Added ["  ime prezime jmbg", "  "] (id: "  ") to the db. "
       [:a {:href (str "/location/" id)} "See for yourself"]
       "."])))





(defn svi-zaposleni
  [req]
  (let [all-rec (db/select-all req)]
    (page/html5
      (gen-page-head "Svi zaposleni")

      [:h1 "Svi zaposleni"]
      [:table
       [:tr [:th "Ime"] [:th "Prezime"] [:th "jmbg"]]
       (for [zapis all-rec]
         [:tr [:td (:ime zapis)] [:td (:prezime zapis)] [:td (:jmbg zapis)]])])))

(defn home-page
  [req]
  (page/html5
    (gen-page-head "Home")
    header-links
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

(def header-links
  [:div#header-links
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
    header-links
    [:h1 "Home"]
    [:p "Webapp to store and display some 2D (x,y) locations."]))

(defn add-location-page
  []
  (page/html5
    (gen-page-head "Add a Location")
    header-links
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
      header-links
      [:h1 "Added a Location"]
      [:p "Added [" x ", " y "] (id: " id ") to the db. "
       [:a {:href (str "/location/" id)} "See for yourself"]
       "."])))

(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (page/html5
      (gen-page-head (str "Location " loc-id))
      header-links
      [:h1 "A Single Location"]
      [:p "id: " loc-id]
      [:p "x: " x]
      [:p "y: " y])))

(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (page/html5
      (gen-page-head "All Locations in the db")
      header-links
      [:h1 "All Locations"]
      [:table
       [:tr [:th "id"] [:th "x"] [:th "y"]]
       (for [loc all-locs]
         [:tr [:td (:id loc)] [:td (:x loc)] [:td (:y loc)]])])))

)

