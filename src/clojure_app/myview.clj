(ns clojure-app.myview
  (:require
    [hiccup.core :refer [html h]]
    ))

(defn main [req]
  (html
    [:div
     [:h1 "Hello Hiccup Page with Routing!"]
     [:p "What would you like to do?"]
     [:p [:a {:href "./get-form.html"} "Submit a GET request"]]
     [:p [:a {:href "./post-form.html"} "Submit a POST request"]]]))

(defn get-form [req]
  (html
    [:div
     [:h1 "Hello GET Form!"]
     [:p "Submit a message with GET"]
     [:form {:method "get" :action "get-submit"}
      [:input {:type "text" :name "name"}]
      [:input {:type "submit" :value "submit"}]]
     [:p [:a {:href ".."} "Return to main page"]]]))

(defn post-form [req]
  (html
    [:div
     [:h1 "Hello POST Form!"]
     [:p "Submit a message with POST"]
     [:form {:method "post" :action "post-submit"}
      [:input {:type "text" :name "name"}]
      [:input {:type "submit" :value "submit"}]]
     [:p [:a {:href ".."} "Return to main page"]]]))

(defn display-result [req]
  (let [{:keys [params uri]} req
        param-name (get params "name")
        req-type (if (= uri "/get-submit") "GET" "POST")]
    (html
      [:div
       [:h1 "Hello " (h param-name) "!"]
       [:p "Submitted via a " req-type " request." req]
       [:p [:a {:href ".."} "Return to main page"]]])))

(defn not-found []
  (html
    [:h1 "404 Error!"]
    [:b "Page not found!"]
    [:p [:a {:href ".."} "Return to main page"]]))


