(ns clojure-app.views
  (:require [clojure-app.db :as db]
            [hiccup.page :as page]
            [ring.middleware.json :as json]
            [ring.util.anti-forgery :as util]
            )
  )
(defn generisi-head-naslov
  [title]
  [:head
   [:title (str "Locations: " title)]
   [:link {:rel "stylesheet" :href "/css/styles.css"}]
   (page/include-js "/js/jquery.js")
   (page/include-js "/js/bootstrap.js")
   (page/include-js "/js/test1.js")
   (page/include-css "/css/bootstrap.css")
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
(defn dodaj-novi-view
  [req]
  (page/html5
    (generisi-head-naslov "Додај нови запис")
    heder
    req

    [:h1 "Додај нови запис"]
    [:form {:action "/dodaj-novi" :method "POST"}
     (util/anti-forgery-field)
     [:p "Назив ИС: " [:input {:type "text" :name "NazivIS"}]]
     [:p "Фаза животног циклуса: " [:td  [:select {:name "FazaZivotnogCiklusa"}
                                              [:option {:value "У развоју"} "У развоју"]
                                              [:option {:value "У употреби"} "У употреби"]
                                              [:option {:value "Ван употребе"} "Ван употребе"]
                                              [:option {:value "Модификација"} "Модификација"]]]]
     [:p "Област: " [:input {:type "text" :name "Oblast"}]]
     [:p "Тип: " [:input {:type "text" :name "Tip"}]]
     [:p "Носилац: " [:input {:type "text" :name "Nosilac"}]]
     [:p "Оперативни систем: " [:td  [:select {:name "OperativniSistem"}
                                     [:option {:value "Windows XP"} "Windows XP"]
                                     [:option {:value "Windows 7"} "Windows 7"]
                                     [:option {:value "Windows 10"} "Windows 10"]
                                     [:option {:value "Независан од ОП"} "Независан од ОП"]]]]

     [:p [:input {:type "submit" :value "Додај ИС"}]]]))

(defn Dodaj-novi-is-rezultat
  [{ :keys [NazivIS FazaZivotnogCiklusa Oblast Tip Nosilac OperativniSistem]}]
  (let [id (db/dodaj-novi NazivIS FazaZivotnogCiklusa Oblast Tip Nosilac OperativniSistem)]
    (page/html5
      (generisi-head-naslov "Додата локација")
      heder
      [:h1 "Додата локација"]
      [:p "Успешно додат ИС - "  NazivIS ]
       [:a {:href (str "/detalji/" (:generated_key id))} "Види детаље"]
       )))

(defn obrisi-zapis
  [request]
(page/html5
  (generisi-head-naslov "Obrisan zapis")
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
      (generisi-head-naslov "Сви информациони системи")
      heder
      [:h1 "Сви информациони системи"]
      [:table  {:class "table table-striped table-bordered table-hover table-sm"}
       [:tr  [:th "Назив ИС"] [:th "Фаза животног циклуса"] [:th "Област"] [:th "Тип"] [:th "Носилац"] [:th "Оперативни систем"] [:th "B"] [:th "D"]]
       (for [zapis all-rec]

         [:tr [:td (:nazivis zapis)] [:td (:fazazivotnogciklusa zapis)] [:td (:oblast zapis)] [:td (:tip zapis)] [:td (:nosilac zapis)] [:td (:operativnisistem  zapis)] [:td        [:a {:id "detalji" :class "btn btn-primary":href (str "/detalji/" (:id  zapis)) } "Детаљи"] ] [:td   [:a  {:id "obrisi" :class "btn btn-primary" :href (str "/obrisi/" (:id  zapis)) } "Обриши запис"] ]  ])])))

(defn detalji-is
  [is-id]
  (let [{nazivIs :nazivis tip :tip faza :fazazivotnogciklusa oblast :oblast nosilac :nosilac opsistem :operativnisistem} (db/get-is is-id)]
    (page/html5
      (generisi-head-naslov (str "Location " is-id))
      (page/include-js "javascript/test1.js")
      heder
      [:h1 "Информациони систем:"]
      [:p "Ид: " is-id]
      [:p "Назив ИС: " nazivIs]
      [:p "Област: " oblast]
      [:p "Носилац: " nosilac]
      [:p "Оперативни систем: " opsistem]

      [:p "Тип: " tip])))



(defn pocetna
  [req]
  (page/html5
    (generisi-head-naslov "Почетна")
    heder
    [:h1 "Добродошли на РЕГИС"]
    [:p "РЕГИС (регистар информационих система) представља информациони систем у којем се води евиденција о свим ИС који су били или су и даље у употреби"]))




