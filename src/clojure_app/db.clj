(ns clojure-app.db
  ( :require  [clojure.java.jdbc :as sql]
  ))




(def mysql-db {
               :subprotocol "mysql"
               :subname     "//localhost/clojure"
               :user        "root"
               :password    "root"})



(defn dodaj-novi
  [x y z]
  (let [results (sql/insert! mysql-db :zaposleni {:Ime x :Prezime y :jmbg z}) ]))


(defn select-all [req]
(sql/query mysql-db
           ["SELECT * FROM zaposleni"]
           ))
(comment
(defn add-location-to-db
  [x y]
  (let [results (jdbc/insert! db-spec :locations {:x x :y y})]
    (assert (= (count results) 1))
    (first (vals (first results)))))
)

(sql/insert! mysql-db
             :zaposleni {:Ime "Imee" :Prezime "Prezime" :jmbg "221231"})


(sql/delete! mysql-db
             :zaposleni ["Prezime = ? " "Prezime"])

(sql/update! mysql-db
             :zaposleni
             {:ime 40}
             ["prezime = ? " "Jovic"])